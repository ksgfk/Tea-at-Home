package com.github.ksgfk.teaathome.control.impl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public class ConnectionTeaShop {
	private static String Driver="com.mysql.cj.jdbc.Driver";
	private static String Url="jdbc:mysql://localhost:3306/tea_shop?serverTimezone=UTC";
	private static String User="root";
	private static String Password="123456";
	private Connection conn=null;
	private PreparedStatement pre=null;
	private ResultSet resu=null;
	public void getConnection(){
		try {
			Class.forName(Driver);
			conn=DriverManager.getConnection(Url,User,Password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.print("连接错误");
		}
	}

	public void close() {
		try {
			if(resu!=null) resu.close();
			if(pre!=null) pre.close();
			if(conn!=null) pre.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean updata(String sql,Object...args) {
		int row=0;
		try {
			getConnection();
			pre=conn.prepareStatement(sql);
			for(int i=0;args!=null&&i<args.length;i++) {
				pre.setObject(i+1, args[i]);
			}
			row=pre.executeUpdate();
		}catch(Exception e) {
			
		}
		finally{
			close();
		}
		if(row>=1) 
			return true;
		else
			return false;
		
	}
	public ResultSet query(String sql,Object...args) {
		try {
			getConnection();
			pre=conn.prepareStatement(sql);
			for(int i=0;args!=null&&i<args.length;i++) {
				pre.setObject(i+1, args[i]);
			}
			resu=pre.executeQuery();
		}
		catch(Exception e) {
			
		}
		finally {
			
		}
		return resu;		
	}
}