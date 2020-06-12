package com.online.study.controller.manage;

import com.online.study.common.ServerResponse;
import com.online.study.entity.SysPermission;
import com.online.study.service.SysPermissionService;
import com.online.study.service.impl.SysPermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/permission")
public class PermissionController {

    @Autowired
    private SysPermissionService permissionService;

    @PostMapping("/add_parent_permission")
    public ServerResponse addParentPermission(SysPermission permission){

        return permissionService.addParentPermission(permission);
    }
    @PostMapping("/add_child_permission")
    public ServerResponse addChildPermission(SysPermission permission){
        return permissionService.addPermission(permission);
    }
    @RequestMapping("/get_list")
    public ServerResponse getList(){

        return permissionService.getList();
    }

    @RequestMapping("/get_parent_permission")
    public ServerResponse get_parent_permission(){
        return permissionService.selectParentPermission();
    }
    @RequestMapping("/get_tree_list")
    public ServerResponse getTreeList(){
        return permissionService.getTreeList();
    }
}
