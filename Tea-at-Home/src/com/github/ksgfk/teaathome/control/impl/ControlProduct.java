package com.github.ksgfk.teaathome.control.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.ksgfk.teaathome.control.inter.ControlProductInter;
import com.github.ksgfk.teaathome.models.ConnectionTeaShop;
import com.github.ksgfk.teaathome.models.Product;

public class ControlProduct implements ControlProductInter {
	private ConnectionTeaShop conn=null;
	public ControlProduct() {
		conn= new ConnectionTeaShop();
	}
	@Override
	public boolean add(Product product) {
		String sql="insert into product(name,count,depository_id) values(?,?,?)";//product
		return conn.updata(sql, product.getName(),product.getCount(),product.getDepositoryId());
	}

	@Override
	public boolean deleteId(int id) {
		String sql="delete from product where id=?";
		return conn.updata(sql, id);
	}

	@Override
	public boolean updata(Product product) {
		String sql="updata product set name=?,count=?,depository_id=? where id=?";
		return conn.updata(sql, product.getName(),product.getCount(),product.getDepositoryId(),product.getId());
	}

	@Override
	public Product findid(int id) {
		String sql="select * from product where id= ?";
		ResultSet res=conn.query(sql, id);
		Product item=null;
		try {
			if(res!=null&&res.next()) {
				item= new Product(res.getInt("id"),res.getString("name"),res.getInt("count"),res.getInt("depository_id"),res.getBigDecimal("price"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
		return item;
	}

	@Override
	public List<Product> findname(String name) {
		String sql="select * from product where name like ?";
		ResultSet res=conn.query(sql, "%"+name+"%");
		List<Product> list=new ArrayList<Product>();
		try {
			while(res!=null&&res.next()) {
				list.add(new Product(res.getInt("id"),res.getString("name"),res.getInt("count"),res.getInt("depository_id"),res.getBigDecimal("price")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
		return list;
	}

}
