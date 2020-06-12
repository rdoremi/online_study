package com.online.study.vo;

import com.online.study.entity.SysPermission;


import java.util.List;

public class SysPermissionVo {
   private SysPermission permission;
   private List<SysPermission> cList;

    public SysPermission getPermission() {
        return permission;
    }

    public void setPermission(SysPermission permission) {
        this.permission = permission;
    }

    public List<SysPermission> getcList() {
        return cList;
    }

    public void setcList(List<SysPermission> cList) {
        this.cList = cList;
    }
}
