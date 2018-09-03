package com.nick.bean.auth;

import java.util.Date;

public class Button implements Privilege {
    private int id;
    private String name;
    private int sort;
    private Date createTime;


    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public short getType() {
        return 3;
    }

    @Override
    public int getSort() {
        return sort;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
