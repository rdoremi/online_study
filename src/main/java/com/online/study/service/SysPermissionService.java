package com.online.study.service;

import com.online.study.common.ServerResponse;
import com.online.study.entity.SysPermission;
import com.online.study.vo.SysPermissionVo;

import java.util.List;

/**
 * (SysPermission)表服务接口
 *
 * @author makejava
 * @since 2020-04-06 11:28:16
 */
public interface SysPermissionService {

    ServerResponse addParentPermission(SysPermission permission);
    ServerResponse addPermission(SysPermission permission);
    ServerResponse getList();
    ServerResponse<List<SysPermissionVo>> getTreeList();
    ServerResponse selectParentPermission();

}