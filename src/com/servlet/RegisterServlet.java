package com.servlet;

import com.Dao.UserDao;
import com.sun.deploy.util.ArrayUtil;
import com.user.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errMsg="";
        int rows=0;
        // 1、设置编码（请求和响应）
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        try {
            //获取注册界面的数据
            String username=req.getParameter("username");
            String password=req.getParameter("password");
            String conPassword=req.getParameter("password2");
            String sex =req.getParameter("sex");
            String[] hobbys =req.getParameterValues("hobbys");
            String phone =req.getParameter("phone");
            String email =req.getParameter("email");
            String addrs =req.getParameter("addrs");
            //创建Dao对象
            UserDao userDao=new UserDao();
            //对数据做验证判断
            if (username==null||password==null||sex==null||hobbys==null
                    ||phone==null||email==null||addrs==null
                    ||"".equals(password)||"".equals(sex)||"".equals(hobbys)
                    ||"".equals(phone)||"".equals(email)||"".equals(addrs)
                    ||"".equals(username)
                 ){
                throw new RuntimeException("信息不能为空");
            }
            //密码一致判断
            if (!password.equals(conPassword)){
                throw new RuntimeException("密码要一致");
            }
            //用户名唯一判断
            if (username.equals(userDao.findByUser(username).getUsername())){
                throw new RuntimeException("该用户名已经存在");
            }
            //获得用户对象以及设置数据
            User user=new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setSex(sex);
            String hobby= Arrays.toString(hobbys);
            user.setHobbys(hobby);
            user.setPhone(phone);
            user.setEmail(email);
            user.setAddrs(addrs);
            //执行增加用户数据的方法
            rows=userDao.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            errMsg=e.getMessage();
        }
        PrintWriter out=resp.getWriter();
        if (rows>0){
            out.println("<script type='text/javascript'>alert('注册成功');location.href='login.jsp';</script>");
        }else {
            out.println("<script type='text/javascript'>alert('注册失败："+errMsg+"');history.back();</script>");
        }
    }
}
