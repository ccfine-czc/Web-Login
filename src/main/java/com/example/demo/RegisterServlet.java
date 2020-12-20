package com.example.demo;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="RegisterServlet",value = "/register-servlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        String account=request.getParameter("account");
        String pwd=request.getParameter("password");
        String phone=request.getParameter("phone_number");
        Oracle oracle=new Oracle();
        if(oracle.register(account,pwd,phone)){
            response.sendRedirect("http://localhost:8080/demo_war_exploded/Web/ok.html");
        }else{
            response.sendRedirect("http://localhost:8080/demo_war_exploded/Web/register.html");
        }
    }
}
