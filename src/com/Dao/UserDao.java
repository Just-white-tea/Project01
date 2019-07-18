package com.Dao;

import com.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    BaseDao baseDao=new BaseDao();
    //插入数据
    public int insertUser(User user){
        int rows=0;
        //创建sql语句集
        String sql="insert into user(username,password,sex,hobbys,phone,email,addrs) values (?,?,?,?,?,?,?)";
        //添加数据
        List <Object> paramList=new ArrayList<>();
        paramList.add(user.getUsername());
        paramList.add(user.getPassword());
        paramList.add(user.getSex());
        paramList.add(user.getHobbys());
        paramList.add(user.getPhone());
        paramList.add(user.getEmail());
        paramList.add(user.getAddrs());
        rows=baseDao.executeUpdate(sql,paramList);
        return rows;
    }
    //查询数据
    public User findByUser (String username){
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pra = null;
        User entity=new User();
        try {
            // 1、获得连接对象
            conn = baseDao.getConnection();
            // 2、获得预编译语句集
           String sql=" select * from user where username= ? ";
           //获得编译语句
            pra=conn.prepareStatement(sql);
            //设置占位符的值
            pra.setObject(1,username);
            //执行语句
            rs = pra.executeQuery();
            if (rs.next()){
                entity.setUsername(rs.getString("username"));
                entity.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baseDao.closeAll(conn,pra,rs);
        }
        return entity;
    }






}



