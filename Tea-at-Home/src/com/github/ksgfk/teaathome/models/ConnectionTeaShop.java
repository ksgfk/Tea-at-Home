package com.github.ksgfk.teaathome.models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.github.ksgfk.teaathome.utility.BatchSQL;

import sun.security.jca.GetInstance.Instance;
public class ConnectionTeaShop {
	private static String Driver="com.mysql.jdbc.Driver";
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
			System.out.print("初始化异常");
		}
	}

	public void close() {
		try {
			if(resu!=null) resu.close();
			if(pre!=null) pre.close();
			if(conn!=null) conn.close();
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
			e.printStackTrace();
		}
		finally{
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
			e.printStackTrace();
		}
		finally {

		}
		return resu;
	}
	public boolean updataBatch(String sql,Object arg) {
		List<Object> list=null;
		System.out.print(list);
		if(arg instanceof List) {
			list=(List<Object>)arg;
		}
		try {
			getConnection();
			pre=conn.prepareStatement(sql);
			for(Object item:list) {
				if(item instanceof ShoppingCart) {
					BatchSQL.set((ShoppingCart)item, pre);
				}
				else if(item instanceof BuyInfo) {
					BatchSQL.set((BuyInfo)item, pre);
				}
				pre.addBatch();
			}
			pre.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
			close();
			return false;
		}finally {
			close();
		}
		return true;
	}
}