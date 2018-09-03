package com.nick.bean.auth;

import java.util.Date;

public class Menu implements MenuPrivilege{

    private int id;
    private String name;
    private Menu parent;
    private int sort;
    private Date createTime;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override

    public String getName() {
        return name;
    }

    @Override
    public short getType() {
        return this.parent == null ? (short) 1 : (short) 2;
    }

    @Override
    public int getSort() {
        return sort;
    }


    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
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

    @Override
    public Integer getParentId() {
        return this.parent == null ? null : this.parent.getId();
    }
}
