package com.online.study.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.study.service.impl.UserDetailsServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

  /*  @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/
  @Autowired
  private ObjectMapper objectMapper;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests()
                .antMatchers("/layui/**","/static/**","/xadmin/**","/admin/page/login","/fore/**","/webrtc/**")
                .permitAll()
                .antMatchers("/admin/**","/sys/**").authenticated()
                .anyRequest().permitAll();
        http.formLogin().loginPage("/admin/page/login").loginProcessingUrl("/login").successHandler(new AuthenticationSuccessHandler() {


            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

                httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
                httpServletResponse.setHeader("Access-Control-Allow-Methods","*");
                httpServletResponse.setContentType("application/json;charset-UTF-8");
                httpServletResponse.setStatus(HttpStatus.OK.value());
                httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
            }
        }).failureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {


                if ("json".equals("json")){
                    httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
                    httpServletResponse.setHeader("Access-Control-Allow-Methods","*");
                    httpServletResponse.setContentType("application/json;charset-UTF-8");
                    httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                    //httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));

                    Map<String,Object> map = new HashMap<>();
                    if (e instanceof LockedException){
                        map.put("message","账号被锁定,登录失败!");
                    }else if (e instanceof BadCredentialsException){
                        map.put("message","账号或密码错误,登录失败!");
                    }else if (e instanceof DisabledException){
                        map.put("message","账号被禁用,登录失败!");
                    }else if (e instanceof AccountExpiredException){
                        map.put("message","账号已过期,登录失败!");
                    }else {
                        map.put("message","登录失败!");
                    }
                }else {

                }

            }
        }).and()
        .logout().permitAll().invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .logoutSuccessHandler(new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                httpServletResponse.sendRedirect("/admin/page/login");
            }
        });
        http.csrf().disable();
        //super.configure(http);
        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                httpServletResponse.setStatus(httpServletResponse.SC_FORBIDDEN);
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.sendRedirect("/admin/page/403");
            }
        });
    }

}
