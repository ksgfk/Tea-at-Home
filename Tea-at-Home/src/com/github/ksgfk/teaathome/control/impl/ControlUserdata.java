package com.github.ksgfk.teaathome.control.impl;

import com.github.ksgfk.teaathome.control.inter.ControlUserdataInter;
import com.github.ksgfk.teaathome.models.ConnectionTeaShop;
import com.github.ksgfk.teaathome.models.ShoppingCart;
import com.github.ksgfk.teaathome.models.Userdata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControlUserdata implements ControlUserdataInter {
	private ConnectionTeaShop conn=null;
	public ControlUserdata() {
		// TODO Auto-generated constructor stub
		conn=new ConnectionTeaShop();
	}
	@Override
	public boolean add(Userdata data) {
		String sql="insert into shopping_cart(user_id,product_id,count) values(?,?,?)";
		List<ShoppingCart> list=data.getCar();
		boolean row=false;
		if(list==null) return false;
		for(ShoppingCart item:list) {
			row=conn.updata(sql, item.getUserId(),item.getProductId(),item.getCount());
		}
		return row;
	}

	@Override
	public boolean deleteuserid(int userid) {
		String sql="delete from shopping_cart where user_id=?";
		return conn.updata(sql, userid);
	}
	@Override
	public boolean deleteproductid(int productid) {
		String sql="delete from shopping_cat where product_id=?";
		return conn.updata(sql, productid);
	}
	@Override
	public boolean deleteid(int id) {
		String sql="delete from shopping_cat where id=?";
		return conn.updata(sql, id);
	}

	@Override
	public boolean updata(Userdata data) {
		String sql="updata shopping_cart set user_id=?,product_id=?,count=? where id=?";
		List<ShoppingCart> list=data.getCar();
		boolean row=false;
		if(list==null) return false;
		for(ShoppingCart item:list) {
			row=conn.updata(sql, item.getUserId(),item.getProductId(),item.getCount(),item.getId());
		}
		return row;
	}

	@Override
	public Userdata findUserid(int uesrid) {
		Userdata item=new Userdata(null,uesrid);
		String sql="delete from shopping_cart where user_id=?";
		List<ShoppingCart> list=new ArrayList<ShoppingCart>();
		ResultSet res=conn.query(sql, uesrid);
		try {
			while(res!=null&&res.next()) {
				list.add(new ShoppingCart(res.getInt("id"),res.getInt("user_id"),res.getInt("product_id"),res.getInt("count")));
			}
			item.setCar(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}


}
