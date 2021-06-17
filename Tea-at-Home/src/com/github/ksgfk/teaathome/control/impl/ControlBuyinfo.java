package com.github.ksgfk.teaathome.control.impl;

import com.github.ksgfk.teaathome.control.inter.ControlBuyinfoInter;
import com.github.ksgfk.teaathome.models.BuyInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControlBuyinfo implements ControlBuyinfoInter {
	private ConnectionTeaShop conn=null;
	public ControlBuyinfo() {
		// TODO Auto-generated constructor stub
		conn=new ConnectionTeaShop();
	}
	@Override
	public boolean add(BuyInfo buyinfo) {
		String sql="insert into buy_info(logistics,pay,product_id,receive,state,user_id) values(?,?,?,?,?,?)";
		return conn.updata(sql, buyinfo.getLogistics(),buyinfo.getPay(),buyinfo.getProductId(),buyinfo.getReceive(),buyinfo.getState(),buyinfo.getUserId());
		
	}

	@Override
	public boolean delete(BuyInfo buyinfo) {
		String sql="delete from buy_info where id=?";
		return conn.updata(sql, buyinfo.getId());
	}

	@Override
	public BuyInfo findid(int id) {
		String sql="select * from buy_info where id=?";
		ResultSet res=conn.query(sql, id);
		BuyInfo item=null;
		try {
			if(res!=null&&res.next()) {
			  item=new BuyInfo(res.getInt("id"), res.getInt("user_id") ,res.getInt("product_id") , res.getString("receive"), res.getString("logistics"), res.getInt("state"), res.getString("pay"));
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
	public boolean updata(BuyInfo buyinfo) {
		String sql="updata buy_info set user_id=?,poduct_id=?,receive=?,logistics=?state=?,pay=? where id = ?";
		return conn.updata(sql, buyinfo.getUserId(),buyinfo.getProductId(),buyinfo.getReceive(),buyinfo.getLogistics(),buyinfo.getState(),buyinfo.getPay(),buyinfo.getId());
	}
	@Override
	public List<BuyInfo> findUesrid(int userid) {
		String sql="select * from buy_info where user_id=?";
		List<BuyInfo> list=new ArrayList<BuyInfo>();
		ResultSet res=conn.query(sql, userid);
		try {
			while(res.next()) {
				list.add(new BuyInfo(res.getInt("id"), res.getInt("user_id") ,res.getInt("product_id") , res.getString("receive"), res.getString("logistics"), res.getInt("state"), res.getString("pay")));
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
	public List<BuyInfo> findProductid(int productid) {
		String sql="select * from buy_info where product_id=?";
		List<BuyInfo> list=new ArrayList<BuyInfo>();
		ResultSet res=conn.query(sql, productid);
		try {
			while(res.next()) {
				list.add(new BuyInfo(res.getInt("id"), res.getInt("user_id") ,res.getInt("product_id") , res.getString("receive"), res.getString("logistics"), res.getInt("state"), res.getString("pay")));
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
