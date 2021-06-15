package com.etcxm.www.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.etcxm.www.dao.ProductDAO;
import com.etcxm.www.entity.Product;
import com.etcxm.www.utils.DBUtil;

/**
 * DAO接口的实现类，负责和数据库打交道，对数据进行增删改查的操作
 * 该部分最后会由持久层框架实现。Hibernate，MyBatis
 * @author cyxy
 *
 */
public class ProductDAOImpl implements ProductDAO{
	//操作数据库的工具类，实例化
	private DBUtil dbUtil=new DBUtil();			//Spring会在框架阶段托管这些实例的类

	//新增产品
	@Override
	public int insert(Product product) {
		String sql="insert into tproduct(product_name,product_price,product_store,product_pdate) values(?,?,?,?)";
		int i=dbUtil.update(sql, product.getProductName(),product.getProductPrice(),product.getProductStore(),product.getProductPdate());
		return i;
	}

	//修改产品
	@Override
	public int update(Product product) {
		String sql="update tproduct set product_name=?,product_price=?,product_store=?,product_pdate=? where id=?";
		int i=dbUtil.update(sql, product.getProductName(),product.getProductPrice(),product.getProductStore(),product.getProductPdate(),product.getId());
		return i;
	}

	//删除产品
	@Override
	public int delete(int id) {
		String sql="delete from tproduct where id=?";
		int i=dbUtil.update(sql, id);
		return i;
	}

	//查询所有产品
	@Override
	public List<Product> findAll() {
		String sql="select * from tproduct";	//准备sql语句
		ResultSet resultSet=dbUtil.query(sql);	//执行查询
		//封装结果到实体列表。
		List<Product> list=new ArrayList<Product>();		//实例列表，用来存储Product实体。
		//循环遍历结果集（ResultSet）将实体添加到列表中
		try {
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String productName = resultSet.getString("product_name");
				double productPrice = resultSet.getDouble("product_price");
				int productStore = resultSet.getInt("product_store");
				String productPdate = resultSet.getString("product_pdate");
				Product product=new Product(id, productName, productPrice, productStore, productPdate);
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbUtil.close();
		}
		return list;
	}

	//根据id，查找单条产品信息
	@Override
	public Product findById(int id) {
		String sql="select * from tproduct where id=?";	//准备sql语句
		ResultSet resultSet=dbUtil.query(sql,id);	//执行查询
		Product product=null;
		//循环遍历结果集（ResultSet）将实体添加到列表中
		try {
			while(resultSet.next()){
				String productName = resultSet.getString("product_name");
				double productPrice = resultSet.getDouble("product_price");
				int productStore = resultSet.getInt("product_store");
				String productPdate = resultSet.getString("product_pdate");
				product=new Product(id, productName, productPrice, productStore, productPdate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbUtil.close();
		}
		return product;
	}

	//通过名字模糊查询
	@Override
	public List<Product> findByProductName(String productName) {
		String sql="select * from tproduct where product_name like ?";	//准备sql语句
		productName="%"+productName+"%";
		ResultSet resultSet=dbUtil.query(sql,productName);	//执行查询
		//封装结果到实体列表。
		List<Product> list=new ArrayList<Product>();		//实例列表，用来存储Product实体。
		//循环遍历结果集（ResultSet）将实体添加到列表中
		try {
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String productName1 = resultSet.getString("product_name");
				double productPrice = resultSet.getDouble("product_price");
				int productStore = resultSet.getInt("product_store");
				String productPdate = resultSet.getString("product_pdate");
				Product product=new Product(id, productName1, productPrice, productStore, productPdate);
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbUtil.close();
		}
		return list;
	}

	@Override
	public List<Product> findByProductPrice(double lowPrice, double highPrice) {

		String sql="select * from tproduct where product_price between ? and ?";
		ResultSet resultSet = dbUtil.query(sql, lowPrice, highPrice);
		List<Product> list=new ArrayList<Product>();
		try {
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String productName1 = resultSet.getString("product_name");
				double productPrice = resultSet.getDouble("product_price");
				int productStore = resultSet.getInt("product_store");
				String productPdate = resultSet.getString("product_pdate");
				Product product=new Product(id, productName1, productPrice, productStore, productPdate);
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbUtil.close();
		}
		return list;
	}

	@Override
	public List<Product> findAllByPage(int start, int pageSize) {

		String sql="select * from tproduct order by id limit ?,?";
		ResultSet resultSet=dbUtil.query(sql, start, pageSize);
		List<Product> list=new ArrayList<Product>();
		try {
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String productName1 = resultSet.getString("product_name");
				double productPrice = resultSet.getDouble("product_price");
				int productStore = resultSet.getInt("product_store");
				String productPdate = resultSet.getString("product_pdate");
				Product product=new Product(id, productName1, productPrice, productStore, productPdate);
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbUtil.close();
		}
		return list;
	}

	@Override
	public int count() {
		String sql="select count(*) from tproduct";
		ResultSet resultSet = dbUtil.query(sql);
		int allCount=0;
		try {
			while(resultSet.next()){
				allCount=resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbUtil.close();
		}
		return allCount;
	}

}
