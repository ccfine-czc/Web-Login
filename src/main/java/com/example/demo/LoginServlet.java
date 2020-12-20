package com.example.demo;

import com.google.gson.Gson;
import com.example.demo.LoginResponse;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@WebServlet(name="LoginServlet",value = "/login-servlet")
public class LoginServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        String account=request.getParameter("username");
        String pwd=request.getParameter("userpsw");
        Oracle oracle=new Oracle();
        if(oracle.login(account,pwd)){
            response.sendRedirect("http://localhost:8080/demo_war_exploded/Web/ok.html");
        }else{
            response.sendRedirect("http://localhost:8080/demo_war_exploded/Web/login.html");
        }
//        LoginResponse loginResponse=new LoginResponse();
//
//        loginResponse.setFig("0");
//        loginResponse.setMsg("登陆成功");
//
////        String result=new Gson().toJson(loginResponse);
//        System.out.println("返回的结果+result");
//
////        response.setCharacterEncoding("utf-8");
////        response.setContentType("application/json;charset=utf-8");
//
////        try{
////            response.getWriter().write("result");
////        }catch (IOException e){
////            e.printStackTrace();
////        }
//        response.sendRedirect("http://localhost:8080/demo_war_exploded/Web/ok.html");
    }
}
