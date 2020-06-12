package com.online.study.controller.manage;

import com.online.study.common.ServerResponse;
import com.online.study.entity.SysRole;
import com.online.study.service.SysRoleService;
import com.online.study.service.impl.SysRoleServiceImpl;
import com.online.study.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/role")
public class RoleController {

    @Autowired
    private SysRoleService roleService;

    @PostMapping("/add")
    public ServerResponse addRole(RoleVo role){

        return roleService.addRole(role);
    }

    @RequestMapping("/get_list")
    public ServerResponse getList(){
        return roleService.getList();
    }
    @RequestMapping("/delete")
    public ServerResponse delete(Integer id){
        return roleService.deleteById(id);
    }
}
