package com.online.study.common;

import com.google.common.collect.Sets;

import java.util.Set;

public class Const {

    public static final String CURRENT_USER = "sys";
    public static final String FORE_CURRENT_USER = "foreCurrent";

    public interface Role{
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_ADMIN = 1;//管理员
        int ROLE_SUPER_ADMIN = 2;//超级管理员

    }

    public interface UserRole{
        int ROLE_STUDENT = 0;//学生
        int ROLE_TEACHER = 1;//教师
        int ROLE_ADMIN = 2;//学校管理员
        int ROLE_MANAGE = 2;//学院管理员
    }

    public interface Status{
        int VALID = 2;//启用
        int DISENABLE = 0;//未启用
        int LOCKED = 1;
    }

}
