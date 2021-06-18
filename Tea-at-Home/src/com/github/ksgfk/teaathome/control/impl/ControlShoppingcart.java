package com.github.ksgfk.teaathome.control.impl;

import com.github.ksgfk.teaathome.control.inter.ControlShoppingcartInter;
import com.github.ksgfk.teaathome.models.ConnectionTeaShop;
import com.github.ksgfk.teaathome.models.ShoppingCart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ControlShoppingcart implements ControlShoppingcartInter {
	private ConnectionTeaShop conn=null;
	public ControlShoppingcart() {
		// TODO Auto-generated constructor stub
		conn=new ConnectionTeaShop();
	}
	
	@Override
	public boolean add(ShoppingCart cart) {
		String sql="insert into shopping_cart(user_id,product_id,count) values(?,?,?)";
		return conn.updata(sql, cart.getUserId(),cart.getProductId(),cart.getCount());
	}

	@Override
	public boolean delete(int id) {
		String sql="delete from shopping_cart where id=?";
		return conn.updata(sql, id);
	}

	@Override
	public List<ShoppingCart> finduserid(int userid) {
		String sql="select * from shopping_cart where user_id=?";
		ResultSet res=conn.query(sql, userid);
		List<ShoppingCart> list=new ArrayList<ShoppingCart>();
		try {
			while(res!=null&&res.next()) {
				list.add(new ShoppingCart(res.getInt("id"),res.getInt("user_id"),res.getInt("product_id"),res.getInt("count")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return list;
	}

	@Override
	public boolean updata(ShoppingCart cart) {
		String sql="updata  shopping_cart set user_id=?,product_id=?,count=? where id=?";
		return conn.updata(sql, cart.getUserId(),cart.getProductId(),cart.getCount());
	}

	@Override
	public ShoppingCart findid(int id) {
		String sql="select * from shopping_cart where id=?";
		ResultSet res=conn.query(sql, id);
		ShoppingCart item=null;
		try {
			if(res!=null&&res.next()) {
				item=new ShoppingCart(res.getInt("id"),res.getInt("user_id"),res.getInt("product_id"),res.getInt("count"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return item;

	}

	@Override
	public  ShoppingCart find(int userid, int productid) {
		String sql="select * from shopping_cart where productid=? and userid= ?";
		ResultSet res=conn.query(sql, userid,productid);
		ShoppingCart item=null;
		try {
			if(res!=null&&res.next()) {
				item=new ShoppingCart(res.getInt("id"),res.getInt("user_id"),res.getInt("product_id"),res.getInt("count"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return item;
	}

	

}
