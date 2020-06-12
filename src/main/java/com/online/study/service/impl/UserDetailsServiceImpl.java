package com.online.study.service.impl;

import com.google.common.collect.Lists;
import com.online.study.common.Const;
import com.online.study.dao.SysPermissionDao;
import com.online.study.dao.SysRoleDao;
import com.online.study.dao.SysRolePermissionDao;
import com.online.study.dao.SysUserDao;
import com.online.study.entity.SysPermission;
import com.online.study.entity.SysRolePermission;
import com.online.study.entity.SysUser;
import com.online.study.vo.SysUserLogin;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysRolePermissionDao sysRolePermissionDao;

    @Autowired
    private SysPermissionDao permissionDao;

    @Override
    public UserDetails loadUserByUsername(String tel) throws UsernameNotFoundException {


        SysUser user = sysUserDao.selectByTel(tel);
        if (user == null){
            throw new AuthenticationCredentialsNotFoundException("用户不存在");
        }else if (user.getStatus() == Const.Status.DISENABLE){
            throw new LockedException("用户已锁定");
        }
        SysUserLogin userLogin = new SysUserLogin();
        BeanUtils.copyProperties(user,userLogin);

        List<SysRolePermission> rolePermissionList = sysRolePermissionDao.selectListByRolId(user.getRoleId());
        List<SysPermission> sysPermissionList = Lists.newArrayList();

        for (SysRolePermission sysRolePermission : rolePermissionList) {
            List<SysPermission> permissionList = permissionDao.selectByParentId(sysRolePermission.getPermissionId());
            for (SysPermission permission : permissionList) {
                sysPermissionList.add(permission);
            }
        }
        userLogin.setPermissions(sysPermissionList);
        return userLogin;
    }
}
