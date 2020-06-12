package com.online.study.service;

import com.online.study.common.ServerResponse;
import com.online.study.entity.SysRole;
import com.online.study.vo.RoleVo;

import java.util.List;

/**
 * (SysRole)表服务接口
 *
 * @author makejava
 * @since 2020-04-06 11:28:55
 */
public interface SysRoleService {


    ServerResponse addRole(RoleVo role);

    ServerResponse getList();


    ServerResponse deleteById(Integer id);
}