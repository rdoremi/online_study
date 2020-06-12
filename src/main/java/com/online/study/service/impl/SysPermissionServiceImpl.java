package com.online.study.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.online.study.common.ResponseCode;
import com.online.study.common.ServerResponse;
import com.online.study.entity.SysPermission;
import com.online.study.dao.SysPermissionDao;
import com.online.study.service.SysPermissionService;
import com.online.study.vo.SysPermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (SysPermission)表服务实现类
 *
 * @author makejava
 * @since 2020-04-06 11:28:19
 */
@Service("sysPermissionService")
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    private SysPermissionDao sysPermissionDao;

    @Override
    public ServerResponse addParentPermission(SysPermission permission) {
        int check = sysPermissionDao.checkName(permission.getName());
        if(check > 0){
            return ServerResponse.createByErrorMessage("该权限已存在");
        }
        permission.setParentId(0);
        int result = sysPermissionDao.insert(permission);
        if (result > 0){
            return ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getDesc());
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),"添加权限失败");
    }

    @Override
    public ServerResponse addPermission(SysPermission permission) {
        int check = sysPermissionDao.checkName(permission.getName());
        if(check > 0){
            return ServerResponse.createByErrorMessage("该权限已存在");
        }
        int result = sysPermissionDao.insert(permission);
        if (result > 0){
            return ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getDesc());
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }
    @Override
    public ServerResponse getList() {
        List<SysPermission> list = sysPermissionDao.selectList();
        /*List<SysPermissionVo> permissionVoList = Lists.newArrayList();
        for (SysPermission permission : list) {
            SysPermissionVo permissionVo = new SysPermissionVo(permission.getId(),permission.getParentId(),permission.getName(),permission.getPermission());
            permissionVoList.add(permissionVo);
        }*/

        return ServerResponse.createBySuccess(list);
    }

    @Override
    public ServerResponse selectParentPermission() {
        List<SysPermission> permission = sysPermissionDao.selectByParentId(0);
        return ServerResponse.createBySuccess(permission);
    }

    @Override
    public ServerResponse<List<SysPermissionVo>> getTreeList() {
        List<SysPermission> list = sysPermissionDao.selectList();
        List<SysPermissionVo> permissionVoParentList = Lists.newArrayList();
        for (SysPermission permission : list) {
            if (permission.getParentId() == 0){
                SysPermissionVo pVo = new SysPermissionVo();
                pVo.setPermission(permission);
                permissionVoParentList.add(pVo);
            }
        }

        for (int i = 0; i < permissionVoParentList.size(); i++) {
            List<SysPermission> sysList = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                if (permissionVoParentList.get(i).getPermission().getId() == list.get(j).getParentId()){
                    sysList.add(list.get(j));


                }
            }
            permissionVoParentList.get(i).setcList(sysList);
        }
        /*for (SysPermission permission : list) {
            for (SysPermissionVo sysPermission : permissionVoParentList) {
                if (permission.getParentId() == sysPermission.getPermission().getId()){

                }
            }
        }*/


        return ServerResponse.createBySuccess(permissionVoParentList);
    }
}