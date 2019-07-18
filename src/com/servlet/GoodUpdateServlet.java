package com.servlet;

import com.Dao.GoodsDao;
import com.good.Goods;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GoodUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errMsg="";
        int rows=0;//受影响的行数
        GoodsServlet goodsServlet=new GoodsServlet();

        try {
            // 1、设置编码（请求和响应）
            req.setCharacterEncoding("utf-8");
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("text/html;charset=utf-8");
            //使用上传表单获得的商品对象
            Goods goods=goodsServlet.uploadFile(req,resp);
            //创建Dao并执行更新
            GoodsDao goodsDao=new GoodsDao();
            rows=goodsDao.updateGoods(goods);
        } catch (Exception e) {
            e.printStackTrace();
            errMsg=e.getMessage();
        }
        //判断受影响的行数
        PrintWriter out=resp.getWriter();
        if (rows>0){
            out.println("<script type='text/javascript'>alert('修改成功');location.href='goodQueryServlet';</script>");
        }else {
            out.println("<script type='text/javascript'>alert('修改失败："+errMsg+"');history.back();</script>");
        }
    }
}
