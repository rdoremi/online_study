package com.online.study.service.impl;

import com.online.study.common.ServerResponse;
import com.online.study.dao.SysRolePermissionDao;
import com.online.study.entity.SysRole;
import com.online.study.dao.SysRoleDao;
import com.online.study.entity.SysRolePermission;
import com.online.study.service.SysRoleService;
import com.online.study.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (SysRole)表服务实现类
 *
 * @author makejava
 * @since 2020-04-06 11:28:55
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysRolePermissionDao rolePermissionDao;

    @Override
    public ServerResponse addRole(RoleVo role) {
        SysRole sysRole = new SysRole();
        sysRole.setDiscription(role.getDescription());
        sysRole.setName(role.getName());
        int checked = sysRoleDao.checkByName(role.getName());
        if (checked > 0){
            return ServerResponse.createByErrorMessage("该角色已存在");
        }
        int resultRole = sysRoleDao.insert(sysRole);

        if (resultRole > 0){
            SysRole getRole = sysRoleDao.selectByName(role.getName());


            String[] rolePermissionList = role.getPermission().split(",");
            int[] permissionList = new int[rolePermissionList.length];
            for (int i = 0; i < rolePermissionList.length; i++) {
                permissionList[i] = Integer.parseInt(rolePermissionList[i]);
            }


            List<SysRolePermission> list = new ArrayList<>();
            for (int i = 0; i < permissionList.length; i++) {
                SysRolePermission sysRolePermission = new SysRolePermission();
                sysRolePermission.setPermissionId(permissionList[i]);
                sysRolePermission.setRoleId(getRole.getId());
                list.add(sysRolePermission);
            }
            int resultPermission = rolePermissionDao.insertList(list);
            if (resultPermission > 0){
                return ServerResponse.createBySuccessMessage("SUCCESS");
            }
        }
        return ServerResponse.createByErrorMessage("添加角色失败");
    }

    @Override
    public ServerResponse getList() {
        List<SysRole> sysRoleList = sysRoleDao.selectList();
        return ServerResponse.createBySuccess(sysRoleList);
    }

    @Override
    public ServerResponse deleteById(Integer id) {
        int result = sysRoleDao.deleteById(id);
        if (result > 0){
            return ServerResponse.createBySuccessMessage("SUCCESS");
        }
        return ServerResponse.createByErrorMessage("ERROR");
    }
}