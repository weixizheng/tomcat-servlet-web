package com.nick.bean.auth;

import java.util.Date;

public interface Privilege {
    //权限id
    int getId();

    //权限名称
    String getName();

    //权限类型 0: 角色，1:主菜单，2：子菜单 ，3：页面按钮
    short getType();

    //权限顺序
    int getSort();
}
