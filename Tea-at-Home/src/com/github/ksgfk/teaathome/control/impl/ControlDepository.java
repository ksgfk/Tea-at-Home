package com.github.ksgfk.teaathome.control.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import www.Control.inter.ControlDepositoryInter;
import www.models.Depository;
import www.models.Product;

public class ControlDepository implements ControlDepositoryInter {
	
	private ConnectionTeaShop conn=null;
	
	public ControlDepository() {
		conn=new ConnectionTeaShop();
	}
	@Override
	public boolean add(Depository depository) {
		String sql="insert into depository(name,address) values(?,?)";
		return conn.updata(sql, depository.getName(),depository.getAddress());
	}

	@Override
	public boolean delete(int id) {
		String sql="delete form depository where id = ?";
		return conn.updata(sql, id);
	}

	@Override
	public boolean updata(Depository depository) {
		String sql="updata depository set name=?,address=? where id = ?";
		return conn.updata(sql,depository.getName(),depository.getAddress(),depository.getId() );
	}

	@Override
	public Depository findid(int id) {
		String sql="select * from depository where id=?";
		ResultSet res=conn.query(sql, id);
		Depository item=null;
		try {
			if(res!=null&&res.next()) {
				item= new Depository(id, res.getString("name"), res.getString("address"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public boolean addProduct(Product product, int id) {
		product.setDepositoryId(id);
		return new ControlProduct().add(product);
	
	}

	@Override
	public boolean deleteProduct(Product product) {
		return new ControlProduct().deleteId(product.getId());
	}

}
