package com.github.ksgfk.teaathome.control.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import www.Control.inter.ControlBuyinfoInter;
import www.models.Buy_info;

public class ControlBuyinfo implements ControlBuyinfoInter {
	private ConnectionTeaShop conn=null;
	public ControlBuyinfo() {
		// TODO Auto-generated constructor stub
		conn=new ConnectionTeaShop();
	}
	@Override
	public boolean add(Buy_info buyinfo) {
		String sql="insert into buy_info(logistics,pay,product_id,receive,state,user_id) values(?,?,?,?,?,?)";
		return conn.updata(sql, buyinfo.getLogistics(),buyinfo.getPay(),buyinfo.getProductId(),buyinfo.getReceive(),buyinfo.getState(),buyinfo.getUserId());
		
	}

	@Override
	public boolean delete(Buy_info buyinfo) {
		String sql="delete from buy_info where id=?";
		return conn.updata(sql, buyinfo.getId());
	}

	@Override
	public Buy_info findid(int id) {
		String sql="select * from buy_info where id=?";
		ResultSet res=conn.query(sql, id);
		Buy_info item=null;
		try {
			if(res!=null&&res.next()) {
			  item=new Buy_info(res.getInt("id"), res.getInt("user_id") ,res.getInt("product_id") , res.getString("receive"), res.getString("logistics"), res.getInt("state"), res.getString("pay"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
		return item;
	}

	@Override
	public boolean updata(Buy_info buyinfo) {
		String sql="updata buy_info set user_id=?,poduct_id=?,receive=?,logistics=?state=?,pay=? where id = ?";
		return conn.updata(sql, buyinfo.getUserId(),buyinfo.getProductId(),buyinfo.getReceive(),buyinfo.getLogistics(),buyinfo.getState(),buyinfo.getPay(),buyinfo.getId());
	}
	@Override
	public List<Buy_info> findUesrid(int userid) {
		String sql="select * from buy_info where user_id=?";
		List<Buy_info> list=new ArrayList<Buy_info>();
		ResultSet res=conn.query(sql, userid);
		try {
			while(res.next()) {
				list.add(new Buy_info(res.getInt("id"), res.getInt("user_id") ,res.getInt("product_id") , res.getString("receive"), res.getString("logistics"), res.getInt("state"), res.getString("pay")));
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
	public List<Buy_info> findProductid(int productid) {	
		String sql="select * from buy_info where product_id=?";
		List<Buy_info> list=new ArrayList<Buy_info>();
		ResultSet res=conn.query(sql, productid);
		try {
			while(res.next()) {
				list.add(new Buy_info(res.getInt("id"), res.getInt("user_id") ,res.getInt("product_id") , res.getString("receive"), res.getString("logistics"), res.getInt("state"), res.getString("pay")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return list;
	}
}
