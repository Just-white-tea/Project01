package com.Dao;

import java.sql.*;
import java.util.List;

public class BaseDao {
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/shopping?characterEncoding=utf8&useSSL=false&serverTimezone=CST";
    private static final String username = "root";
    private static final String password = "root";

    // 获得连接对象
    public Connection getConnection(){
        try {
            Class.forName(DRIVER_CLASS);
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    // 关闭资源
    public void closeAll(Connection conn, PreparedStatement sta, ResultSet rs){
        try {
            if(rs!=null) rs.close();
            if(sta!=null) sta.close();
            if(conn!=null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //增删改通用代码
    public int executeUpdate(String sql,List<Object> paramList) {
        Connection conn = null;
        PreparedStatement pra = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            //获得连接对象
            conn = this.getConnection();
            //创建sql语句集
            pra = conn.prepareStatement(sql);
            //设置占位符的值
            if (paramList != null && paramList.size() > 0) {
                for (int i = 0; i < paramList.size(); i++) {
                    pra.setObject(i + 1, paramList.get(i));
                }
            }
            //执行语句
            rows = pra.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeAll(conn, pra, rs);
        }
        return rows;
    }
}
