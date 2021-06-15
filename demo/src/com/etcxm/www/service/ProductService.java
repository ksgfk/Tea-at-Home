package com.etcxm.www.service;

import java.util.List;

import com.etcxm.www.entity.Product;

public interface ProductService {

	public List<Product> findAll();		//读取所有产品
	public boolean save(Product product);	//新增产品
	public boolean update(Product product);	//更新产品
	public boolean delete(int id);		//删除产品
	public Product findById(int id);	//查询单条产品
	public List<Product> findByProductName(String productName);	//模糊查询
	public List<Product> findByProductPrice(double lowPrice,double highPrice);	//根据价格范围搜索产品
	public List<Product> findByPage(int pageNum,int pageSize);
	public int count();		//统计记录个数
}
