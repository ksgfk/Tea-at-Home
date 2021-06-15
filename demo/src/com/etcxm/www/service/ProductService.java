package com.etcxm.www.service;

import java.util.List;

import com.etcxm.www.entity.Product;

public interface ProductService {

	public List<Product> findAll();		//��ȡ���в�Ʒ
	public boolean save(Product product);	//������Ʒ
	public boolean update(Product product);	//���²�Ʒ
	public boolean delete(int id);		//ɾ����Ʒ
	public Product findById(int id);	//��ѯ������Ʒ
	public List<Product> findByProductName(String productName);	//ģ����ѯ
	public List<Product> findByProductPrice(double lowPrice,double highPrice);	//���ݼ۸�Χ������Ʒ
	public List<Product> findByPage(int pageNum,int pageSize);
	public int count();		//ͳ�Ƽ�¼����
}
