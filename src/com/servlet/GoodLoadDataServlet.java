package com.servlet;

import com.Dao.GoodsDao;
import com.good.Goods;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GoodLoadDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GoodsServlet goodsServlet=new GoodsServlet();
        int a=Integer.parseInt(req.getParameter("a"));
        String errMsg="";
        try {
            //获取商品编号
            int goodId=Integer.parseInt(req.getParameter("goodId"));
            //根据编号查询信息
            Goods goods=new Goods();
            goods.setGoodId(goodId);
            GoodsDao goodsDao=new GoodsDao();
            List<Goods> list=goodsDao.findByGoods(goods);
            if (list==null&&list.size()<1){
                throw new RuntimeException("该商品编号找不到");
            }
            //设置到作用域中用于修改页面获取
            req.setAttribute("good",list.get(0));
        } catch (RuntimeException e) {
            e.printStackTrace();
            errMsg=e.getMessage();
        }
        if ("".equals(errMsg)){
            if (a==1){
                req.getRequestDispatcher("good_view.jsp").forward(req,resp);
            }else {
                //表示取到数据
                req.getRequestDispatcher("good_update.jsp").forward(req,resp);
            }
        }else {
            PrintWriter out=resp.getWriter();
            out.println("<script type='text/javascript'>alert('数据加载失败："+errMsg+"');history.back();</script>");
        }

    }


}
