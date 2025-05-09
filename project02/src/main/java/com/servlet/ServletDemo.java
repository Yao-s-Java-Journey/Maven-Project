package com.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/hello")
public class ServletDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1. 获取请求参数值
        String name = req.getParameter("name");
        // 2. 响应结果
        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().write("hello, " + name);
    }
}
