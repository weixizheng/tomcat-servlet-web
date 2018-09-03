package com.nick.bean.auth;


import java.util.Date;

public class Role implements Privilege {

    private int id;
    private String name;
    private int sort;
    private Date createTiem;


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
        return 0;
    }

    @Override
    public int getSort() {
        return sort;
    }



    public void setSort(int sort) {
        this.sort = sort;
    }

    public Date getCreateTiem() {
        return createTiem;
    }

    public void setCreateTiem(Date createTiem) {
        this.createTiem = createTiem;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
