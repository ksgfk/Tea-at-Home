package com.etcxm.www.dao;

import java.util.List;

import com.etcxm.www.entity.Product;

/**
 * 数据库访问接口
 * @author cyxy
 * 使用接口是为了实现Java的多态
 */
public interface ProductDAO {
	
	//新增产品接口
	public int insert(Product product);
	//修改产品接口
	public int update(Product product);
	//删除产品的接口
	public int delete(int id);
	//读取所有产品接口
	public List<Product> findAll();
	//根据id读取单条产品信息
	public Product findById(int id);
	
	//可以根据项目的需要，添加新的接口
	//根据产品你名称，模糊查询
	public List<Product> findByProductName(String productName);
	//根据价格查询产品
	public List<Product> findByProductPrice(double lowPrice, double highPrice);
	
	//分页查询
	public List<Product> findAllByPage(int start,int pageSize);
	
	//统计所有记录条数
	public int count();
	

}
