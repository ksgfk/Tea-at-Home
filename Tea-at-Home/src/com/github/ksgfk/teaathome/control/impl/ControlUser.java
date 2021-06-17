package com.github.ksgfk.teaathome.control.impl;

import com.github.ksgfk.teaathome.control.inter.ControlUserInter;
import com.github.ksgfk.teaathome.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControlUser implements ControlUserInter {
	private ConnectionTeaShop conn=null;
	public ControlUser() {
		conn=new ConnectionTeaShop();
	}
	public boolean add(User user) {
		String sql="insert into user(name,password,sex,photo,phone,receive,permission,question,answer) values(?,?,?,?,?,?,?,?,?)";
		return conn.updata(sql,user.getName(),user.getPassword(),user.getSex(),user.getPhoto(),user.getPhone(),user.getReceive(),user.getPermission(),user.getQuestion(),user.getAnswer());
	}
	@Override
	public boolean deleteId(int id) {
		String sql="delete from user where id=?";
		return conn.updata(sql, id);
	}
	@Override
	public boolean deleteName(String name) {
		String sql="delete from user where name=?";
		return conn.updata(sql, name);
	}
	@Override
	public boolean updata(User user) {
		String sql="update user set name=?,password=?,sex=?,photo=?,phone=?,receive=?,permission=?,question=?,answer=? where id=?";
		return conn.updata(sql,user.getName(),user.getPassword(),user.getSex(),user.getPhoto(),user.getPhone(),user.getReceive(),user.getPermission(),user.getQuestion(),user.getAnswer());
	}
	@Override
	public User findid(int id) {
		String sql="select * from user where id=?";
		ResultSet res=conn.query(sql, id);
		try {
			if(res.next()) {
				return (new User(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getInt(8),res.getString(9),res.getString(10) ));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public User findname(String name) {
		String sql="select * from user where name = ?";
		ResultSet res=conn.query(sql, name);
//		List<User> list=new ArrayList<User>();
		try {
			if(res.next()) {
				return (new User(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getInt(8),res.getString(9),res.getString(10) ));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
 
	}
	
}
