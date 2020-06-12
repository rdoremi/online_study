package com.online.study.service;

import com.github.pagehelper.PageInfo;
import com.online.study.common.ServerResponse;
import com.online.study.entity.SysUser;
import java.util.List;

/**
 * (SysUser)表服务接口
 *
 * @author makejava
 * @since 2020-04-06 11:29:24
 */
public interface SysUserService {

  ServerResponse addSysUser(SysUser user);

  ServerResponse deleteSysUser(Long id);

  ServerResponse updateSysUser(SysUser user);

  ServerResponse<PageInfo<SysUser>> selectSysUserList(int pageNum,int pageSize);

}