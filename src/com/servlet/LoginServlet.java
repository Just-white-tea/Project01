package com.servlet;

import com.Dao.UserDao;
import com.user.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取数据
        String username=req.getParameter("username");
        String password=req.getParameter("password");

        //创建dao，并执行查询
        UserDao userDao=new UserDao();
        User user1=userDao.findByUser(username);
        PrintWriter out=resp.getWriter();

        //获得会话
        HttpSession session=req.getSession();

        if (password.equals(user1.getPassword())){
            //将用户对象存入
            session.setAttribute("user",user1);
            out.println("<script type='text/javascript'>alert('登录成功');location.href='goodQueryServlet';</script>");
        }else {
            out.println("<script type='text/javascript'>alert('登录失败');history.back();</script>");
        }

    }
}
