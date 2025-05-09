package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/request")
public class request extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String scheme = req.getScheme();
        System.out.println("scheme = " + scheme);

        String method = req.getParameter("method");
        System.out.println("method = " + method);

        String uri = req.getRequestURI();
        System.out.println("uri = " + uri);

        StringBuffer url = req.getRequestURL();
        System.out.println("url = " + url);

        String queryString = req.getQueryString();
        System.out.println("queryString = " + queryString); // name=Tome&age=18

        String name = req.getParameter("name");
        System.out.println("query parameter = " + name);

        // 获取 post 请求中的数据，可以获取输入流，从而拿到请求体中的数据
        ServletInputStream inputStream = req.getInputStream();
        StringBuilder requestBody = new StringBuilder();
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }
        System.out.println("Received JSON: " + requestBody.toString());

        // 返回响应
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        // resp.setHeader("Content-Type", "application/json; charset=utf-8");
        if (name != null) {
            // 成功
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"message\": \"hello, " + name + "\"}");
        } else {
            // 失败
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\": \"name parameter is null\"}");
        }
    }
}
