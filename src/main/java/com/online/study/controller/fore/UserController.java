package com.online.study.controller.fore;

import com.online.study.common.Const;
import com.online.study.common.ServerResponse;
import com.online.study.entity.User;
import com.online.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ServerResponse toLogin(User user, HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        ServerResponse loginResult = userService.login(user);
        if (loginResult.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,loginResult.getData());
        }
        return loginResult;
    }
    @PostMapping("/register")
    public ServerResponse register(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return userService.register(user);
    }
}
