package com.example.demo;

public class LoginResponse {
//    登录状态，0为成功，否则失败
    private String fig;
//    传递信息
    private String msg;

    public String getFig(){
        return fig;
    }

    public void setFig(String fig){
        this.fig=fig;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg=msg;
    }
}
