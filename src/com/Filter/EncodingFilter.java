package com.Filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private String encoding;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 读取配置文件中配置的编码方式
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1、设置请求的编码
        servletRequest.setCharacterEncoding(encoding);
        // 2、设置响应的编码
        // 服务器发送给客户的内容的编码方式
        servletResponse.setCharacterEncoding(encoding);
        // 浏览器以什么编码方式执行
        servletResponse.setContentType("text/html;charset=utf-8");
        //通过过滤器，返回请求
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
