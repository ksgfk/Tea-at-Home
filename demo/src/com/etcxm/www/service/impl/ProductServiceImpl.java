package com.etcxm.www.service.impl;

import java.util.List;

import com.etcxm.www.dao.ProductDAO;
import com.etcxm.www.dao.impl.ProductDAOImpl;
import com.etcxm.www.entity.Product;
import com.etcxm.www.service.ProductService;

public class ProductServiceImpl implements ProductService{	//ctrl+1
	
	private ProductDAO productDAO=new ProductDAOImpl();

	@Override
	public List<Product> findAll() {
		return productDAO.findAll();
	}

	@Override
	public boolean save(Product product) {
		int i = productDAO.insert(product);
		return i==1?true:false;			//三元表达式，i==1？ 是返回true，否返回false
	}

	@Override
	public boolean update(Product product) {
		int i=productDAO.update(product);
		return i==1?true:false;
	}

	@Override
	public boolean delete(int id) {
		int i=productDAO.delete(id);
		return i==1?true:false;
	}

	@Override
	public Product findById(int id) {
		return productDAO.findById(id);
	}

	@Override
	public List<Product> findByProductName(String productName) {
		return productDAO.findByProductName(productName);
	}

	@Override
	public List<Product> findByProductPrice(double lowPrice, double highPrice) {
		return productDAO.findByProductPrice(lowPrice,highPrice);
	}

	@Override
	public List<Product> findByPage(int pageNum, int pageSize) {
		
		int start=(pageNum-1)*pageSize;		//根据页数计算开始的记录条数是多少
		return productDAO.findAllByPage(start, pageSize);
		
	}

	@Override
	public int count() {
		return productDAO.count();
	}

}
