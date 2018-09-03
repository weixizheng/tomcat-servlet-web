package com.nick.utils;

import java.sql.*;

public final class DBUtils {
    private final static String driver = "com.mysql.jdbc.Driver";
    private final static String url = "jdbc:mysql://localhost:3306/bagevent0611";
    private final static String user = "root";
    private final static String password = "root";

    private static Connection conn;

    static {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if(conn == null) {
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }

    public static void close(PreparedStatement pst, ResultSet rs) {
        try {
            if(rs != null) {
                rs.close();
            }
            if(pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        for(int i =0; i<10; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        System.out.println(getConnection());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

    }
}
