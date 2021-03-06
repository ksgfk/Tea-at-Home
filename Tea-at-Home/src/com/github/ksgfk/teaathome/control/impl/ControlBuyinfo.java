package com.github.ksgfk.teaathome.control.impl;

import com.github.ksgfk.teaathome.control.inter.ControlBuyinfoInter;
import com.github.ksgfk.teaathome.models.BuyInfo;
import com.github.ksgfk.teaathome.models.ConnectionTeaShop;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
			  item=new BuyInfo(res.getInt("id"), res.getInt("user_id") ,res.getInt("product_id") , res.getString("receive"), res.getString("logistics"), res.getInt("state"), res.getDouble("pay"));
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
		String sql="update buy_info set user_id=?,poduct_id=?,receive=?,logistics=?state=?,pay=? where id = ?";
		return conn.updata(sql, buyinfo.getUserId(),buyinfo.getProductId(),buyinfo.getReceive(),buyinfo.getLogistics(),buyinfo.getState(),buyinfo.getPay(),buyinfo.getId());
	}
	@Override
	public List<BuyInfo> findUesrid(int userid) {
		String sql="select * from buy_info where user_id=?";
		List<BuyInfo> list=new ArrayList<BuyInfo>();
		ResultSet res=conn.query(sql, userid);
		try {
			while(res.next()) {
				list.add(new BuyInfo(res.getInt("id"), res.getInt("user_id") ,res.getInt("product_id") , res.getString("receive"), res.getString("logistics"), res.getInt("state"), res.getDouble("pay")));
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
				list.add(new BuyInfo(res.getInt("id"), res.getInt("user_id") ,res.getInt("product_id") , res.getString("receive"), res.getString("logistics"), res.getInt("state"), res.getDouble("pay")));
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
	public boolean addBatch(List<BuyInfo> buyinfolist) {
		String sql="insert into buy_info(user_id,product_id,receive,logistics,state,pay) values(?,?,?,?,?,?)";
		 return conn.updataBatch(sql, buyinfolist);	
	}
	@Override
	public Map<BuyInfo, String> findToProduct(int userid) {
		String sql=" select * from (select * from buy_info where user_id = ?) as buyinfo,product where buyinfo.product_id=product.id";
		//List<Map.Entry<BuyInfo, String>> list= new ArrayList<Map.Entry<BuyInfo, String>>();
		ResultSet res=conn.query(sql, userid);
		Map<BuyInfo , String > M=new TreeMap<BuyInfo,String>();
			try {
				while(res!=null&&res.next()) {
					BuyInfo temp =new BuyInfo(res.getInt("buyinfo.id"), res.getInt("buyinfo.user_id"),res.getInt("buyinfo.product_id") , res.getString("buyinfo.receive"), res.getString("buyinfo.logistics"), res.getInt("buyinfo.state"),res.getInt("buyinfo.pay"));
					String name=res.getString("product.name");
					M.put(temp, name);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				conn.close();
			}
		return M;
	}
}
