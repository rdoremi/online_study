package com.online.study.controller.manage;

import com.online.study.common.ServerResponse;
import com.online.study.entity.SysUser;
import com.online.study.service.SysUserService;
import com.online.study.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('sys:user:add')")
    public ServerResponse addSysUser(SysUser sysUser){

        return sysUserService.addSysUser(sysUser);
    }

    @GetMapping("/get_sys_user_list")
    public ServerResponse getSysUserList(@RequestParam(value = "page",defaultValue = "1") int page,
                                         @RequestParam(value = "limit",defaultValue = "10") int limit){
        return sysUserService.selectSysUserList(page,limit);
    }
    @RequestMapping("/delete")
    public ServerResponse delete(Long id){

        return sysUserService.deleteSysUser(id);
    }
}
