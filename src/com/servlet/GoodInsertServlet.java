package com.servlet;

import com.Dao.GoodsDao;
import com.good.Goods;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GoodInsertServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GoodsServlet goodsServlet=new GoodsServlet();
        int rows=0;
        String errMsg="";
        try {
            // 1、设置编码（请求和响应）
            req.setCharacterEncoding("utf-8");
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("text/html;charset=utf-8");
            //使用上传表单获得的商品对象
            Goods goods=goodsServlet.uploadFile(req,resp);
            //创建Dao
            GoodsDao goodsDao=new GoodsDao();

            //执行增加方法
               rows=goodsDao.insertGoods(goods);
        }catch (Exception e){
            e.printStackTrace();
            errMsg=e.getMessage();
        }
        PrintWriter out=resp.getWriter();
        if (rows>0){
            out.println("<script type='text/javascript'>alert('增加成功');location.href='goodQueryServlet';</script>");
        }else {
            out.println("<script type='text/javascript'>alert('增加失败："+errMsg+"');history.back();</script>");
        }


    }
}
