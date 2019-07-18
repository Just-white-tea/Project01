package com.servlet;

import com.Dao.GoodsDao;
import com.good.Goods;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoodQueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GoodsDao goodsDao=new GoodsDao();
        List<Goods> list=goodsDao.findByGoods(null);

        req.setAttribute("list",list);
        req.getRequestDispatcher("good_list.jsp").forward(req,resp);
    }
}
