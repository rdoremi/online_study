package com.online.study.dao;

import com.online.study.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-06 11:29:24
 */
public interface SysUserDao {

    SysUser queryById(Long id);

    List<SysUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    List<SysUser> queryAll(SysUser sysUser);

    int insert(SysUser sysUser);

    int update(SysUser sysUser);

    int deleteById(Long id);

    int checkSysUser(String tel);

    List<SysUser> selectAllList();

    SysUser selectByTel(String tel);
}