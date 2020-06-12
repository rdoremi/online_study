package com.online.study.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.online.study.common.Const;
import com.online.study.common.ResponseCode;
import com.online.study.common.ServerResponse;
import com.online.study.entity.SysUser;
import com.online.study.dao.SysUserDao;
import com.online.study.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysUser)表服务实现类
 *
 * @author makejava
 * @since 2020-04-06 11:29:24
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public ServerResponse addSysUser(SysUser user) {

        int checkUser = sysUserDao.checkSysUser(user.getTel());
        if (checkUser > 0){
            return ServerResponse.createByErrorMessage("该用户已存在");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setStatus(Const.Status.VALID);
        int result = sysUserDao.insert(user);
        if (result > 0){
            return ServerResponse.createBySuccessCodeMessage(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getDesc());
        }
        return ServerResponse.createByErrorMessage("添加用户失败");
    }

    @Override
    public ServerResponse deleteSysUser(Long id) {
        int result = sysUserDao.deleteById(id);
        if (result > 0){
            return ServerResponse.createBySuccessMessage("SUCCESS");
        }
        return ServerResponse.createByErrorMessage("ERROR");
    }

    @Override
    public ServerResponse updateSysUser(SysUser user) {
        return null;
    }

    @Override
    public ServerResponse<PageInfo<SysUser>> selectSysUserList(int pageNum,int pageSize) {
        Page page = PageHelper.startPage(pageNum,pageSize);
        List<SysUser> sysUserList = sysUserDao.selectAllList();
        PageInfo pageInfo = new PageInfo(page);
        pageInfo.setList(sysUserList);
        return ServerResponse.createBySuccess(pageInfo);
    }
}