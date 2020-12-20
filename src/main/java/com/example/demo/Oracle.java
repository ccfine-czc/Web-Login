package com.example.demo;
import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.sql.*;

public class Oracle {
    static Connection mConnection;
    static Statement mSql;
    static ResultSet mResultSet;

    private Connection connect(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("加载数据库驱动成功");
        }catch (ClassNotFoundException e){
            System.out.println("找不到驱动程序类，加载驱动程序失败");
            e.printStackTrace();
        }
        String dbURL="jdbc:oracle:thin:@localhost:1521:orcl";
        String userName="C##CZC";
        String password="czc";
        try{
            mConnection=DriverManager.getConnection(dbURL,userName,password);
            System.out.println("数据库连接成功");
            return mConnection;
        }catch (SQLException e){
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return mConnection;
    }

    public boolean register(String account,String password,String phone){
        boolean result=false;
        Oracle c=new Oracle();
        mConnection=c.connect();
        try {
            mSql=mConnection.createStatement();
            String  code= "insert into t_user(user_account,user_password,user_phone) values(?,?,?)";
            PreparedStatement pst=mConnection.prepareStatement(code);
            pst.setString(1, account);
            pst.setString(2, password);
            pst.setString(3,phone);
            int i=pst.executeUpdate();
            if(i>0){
                System.out.println("注册成功");
                result=true;
            }else{
                System.out.println("注册失败，用户名重复");
            }
            return result;
        } catch (SQLException e){
            System.out.println("注册失败，用户名重复");
            e.printStackTrace();
            return false;
        }
    }

    public boolean login(String account,String password){
        boolean result=false;
        Oracle c=new Oracle();
        mConnection=c.connect();
        try{
            mSql=mConnection.createStatement();
            String sqlCode="select * from t_user where user_account=? and user_password=?";
            PreparedStatement pst=mConnection.prepareStatement(sqlCode);
            pst.setString(1,account);
            pst.setString(2,password);
            ResultSet rs=pst.executeQuery();
            if(rs.next()) {
                System.out.println("登陆成功");
                return true;
            }else {
                System.out.println("密码或账号错误");
                return false;
            }
        }catch ( java.sql.SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean phoneLogin(String phoneNumber){
        boolean result=false;
        Oracle c=new Oracle();
        mConnection=c.connect();
        try{
            mSql=mConnection.createStatement();
            String sqlCode="select * from t_user where user_phone=?";
            PreparedStatement pst=mConnection.prepareStatement(sqlCode);
            pst.setString(1,phoneNumber);
            ResultSet rs=pst.executeQuery();
            if(rs.next()) {
                System.out.println("登陆成功");
                return true;
            }else {
                System.out.println("密码或账号错误");
                return false;
            }
        }catch ( java.sql.SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
