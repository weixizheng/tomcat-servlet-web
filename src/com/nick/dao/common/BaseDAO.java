package com.nick.dao.common;

import com.nick.utils.DBUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO<T> {

    private Connection conn;
    private Class<T> entryClass;

    public BaseDAO() {
        try {
            conn = DBUtils.getConnection();
            ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
            entryClass = (Class<T>) type.getActualTypeArguments()[0];
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(String sql, List param) {
        this.update(sql, param);
    }

    public void delete(String sql, List param) {
        this.update(sql, param);
    }

    public int update(String sql, List param) {
        int count = 0;
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            this.setParameters(pst, param.toArray());
            count = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(pst, null);
        }
        return count;
    }

    public List<T> findByParam(String sql, List param) {
        List<T> list = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            this.setParameters(pst, param.toArray());
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(this.buildBean(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(pst, rs);
        }
        return list;
    }


    public T findById(String sql, int id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            this.setParameters(pst, id);
            rs = pst.executeQuery();
            if(rs.next()) {
                return buildBean(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(pst, rs);
        }
        return null;
    }

    private void setParameters(PreparedStatement pst, Object ...params) throws SQLException {
        if(params != null) {
            for(int i = 1; i<=params.length; i++) {
                pst.setObject(i, params[i-1]);
            }
        }
    }

    private T buildBean(ResultSet rs) throws SQLException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        T t = null;
        String[] columns = getColumns(rs.getMetaData());
        if(columns != null) {
            t = entryClass.newInstance();
            Method[] methods = entryClass.getMethods();
            for(String col : columns) {
                String methodName = "set" + col;
                for(Method method : methods) {
                    if(methodName.equalsIgnoreCase(method.getName())) {
                        method.invoke(t, rs.getObject(col));
                        break;
                    }
                }
            }
        }
        return t;
    }

    private String[] getColumns(ResultSetMetaData rsmd) throws SQLException {
        String[] columns = null;
        int count = rsmd.getColumnCount();
        if(count > 0) {
            columns = new String[count];
            for(int i = 1; i <= count; i++) {
                columns[i-1] = rsmd.getColumnLabel(i);
            }
        }
        return columns;
    }
}
