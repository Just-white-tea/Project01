package com.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       //向下转型
        HttpServletRequest req =(HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        //获得会话中的对象
        String username=(String) session.getAttribute("username");
        PrintWriter out=resp.getWriter();
        //判断是否为登录和注册以外的页面，如果是，则为true
        String requestURI = req.getRequestURI();
        boolean flag=true;
        flag =flag && !requestURI.startsWith("/login.jsp");
        flag= flag && !requestURI.startsWith("/loginServlet");
        flag= flag && !requestURI.startsWith("/register.jsp");
        flag= flag && !requestURI.startsWith("/RegisterServlet");
        flag= flag && !requestURI.contains(".css");
        flag= flag && !requestURI.contains(".js");
        flag= flag && !requestURI.contains(".png");
        flag= flag && !requestURI.contains(".jpg");

        if (flag) {
            if (username==null || "".equals(username)) {
                out.println("<script type='text/javascript'>alert('请先登录');location.href='login.jsp';</script>");
            }else {
                filterChain.doFilter(req, resp);
            }
        }else {
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
