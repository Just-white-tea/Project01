package com.servlet;

import com.Dao.GoodsDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GoodDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号
        int goodId=Integer.parseInt(req.getParameter("goodId"));
        //运行goodsDao里面的删除方法
        GoodsDao goodsDao=new GoodsDao();
        int rows=goodsDao.deleteGoods(goodId);

        PrintWriter out=resp.getWriter();
        if (rows>0){
            out.println("<script type='text/javascript'>alert('删除成功');location.href='goodQueryServlet';</script>");
        }else {
            out.println("<script type='text/javascript'>alert('删除失败');history.back();</script>");
        }
    }
}
