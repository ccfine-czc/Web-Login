package com.example.demo;

import com.google.gson.Gson;
import com.example.demo.LoginResponse;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.sf.json.JSONObject;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@WebServlet(name="PhoneLoginServlet",value = "/phone_login-servlet")
public class PhoneLoginServlet extends HttpServlet{

    private String phoneNumber=null;
    private int code=0;

    private String mPhoneNumber=null;
    private int mCode=1;

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {

        if(request.getParameter("user_phone")!=null){
            mPhoneNumber=request.getParameter("user_phone");
            mCode=Integer.parseInt(request.getParameter("check_code"));
        }else{
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            //将json字符串转换为json对象
            JSONObject json= JSONObject.fromObject(sb.toString());
            if(json.has("target")&&json.getString("target").equals("send")){
                if(json.has("phone_number")){
                    phoneNumber=json.getString("phone_number");
                    SendSMSUtil send=new SendSMSUtil();
                    send.senSMSUtil(phoneNumber);
                    code=send.getCode();
                }
            }
        }

        if(phoneNumber!=null&&mPhoneNumber!=null){
            if(phoneNumber.equals(mPhoneNumber)&&code==mCode){
                Oracle c=new Oracle();
                if(c.phoneLogin(phoneNumber)){
                    response.sendRedirect("http://localhost:8080/demo_war_exploded/Web/ok.html");
                }
            }else{
                response.sendRedirect("http://localhost:8080/demo_war_exploded/Web/phoneLogin.html");
            }
        }
//}else{
//        response.getOutputStream().write("Failed" .getBytes());
//        }
////        if(json.has("target")&&json.getString("target").equals("login")){
////            if(json.has("phone_number")){
////                mPhoneNumber=json.getString("phone_number");
////                if(json.has("check_code")){
////                    mCode=json.getInt("check_code");
////                    System.out.println(mPhoneNumber);
////                    System.out.println(mCode);
////                    if(phoneNumber!=null&&mPhoneNumber!=null){
////                        if(phoneNumber.equals(mPhoneNumber)&&code==mCode){
////                            Oracle c=new Oracle();
////                            if(c.phoneLogin(phoneNumber)){
////                                response.getOutputStream().write("OK" .getBytes());
////                            }
////                        }
////                    }
////                }
////            }else{
////                response.getOutputStream().write("Failed" .getBytes());
////            }
////        }
//        response.getOutputStream().write("OK" .getBytes());
    }

}