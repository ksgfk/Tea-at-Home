package com.github.ksgfk.teaathome.control.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import www.Control.inter.ControlShoppingcartInter;
import www.models.Shopping_cart;

public class ControlShoppingcart implements ControlShoppingcartInter {
	private ConnectionTeaShop conn=null;
	public ControlShoppingcart() {
		// TODO Auto-generated constructor stub
		conn=new ConnectionTeaShop();
	}
	
	@Override
	public boolean add(Shopping_cart cart) {
		String sql="insert into shopping_cart(user_id,product_id,count) values(?,?,?)";
		return conn.updata(sql, cart.getUserId(),cart.getProductId(),cart.getCount());
	}

	@Override
	public boolean delete(int id) {
		String sql="delete from shopping_cart where id=?";
		return conn.updata(sql, id);
	}

	@Override
	public Shopping_cart finduserid(int userid) {
		String sql="select * from shopping where user_id=?";
		ResultSet res=conn.query(sql, userid);
		Shopping_cart item=null;
		try {
			if(res!=null&&res.next()) {
				item=new Shopping_cart(res.getInt("id"),res.getInt("user_id"),res.getInt("product_id"),res.getInt("count"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public boolean updata(Shopping_cart cart) {
		String sql="updata  shopping_cart set user_id=?,product_id=?,count=? where id=?";
		return conn.updata(sql, cart.getUserId(),cart.getProductId(),cart.getCount());
	}

	@Override
	public Shopping_cart findid(int id) {
		String sql="select * from shopping where id=?";
		ResultSet res=conn.query(sql, id);
		Shopping_cart item=null;
		try {
			if(res!=null&&res.next()) {
				item=new Shopping_cart(res.getInt("id"),res.getInt("user_id"),res.getInt("product_id"),res.getInt("count"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;

	}

}
