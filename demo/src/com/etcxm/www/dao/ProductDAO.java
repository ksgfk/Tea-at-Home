package com.etcxm.www.dao;

import java.util.List;

import com.etcxm.www.entity.Product;

/**
 * ���ݿ���ʽӿ�
 * @author cyxy
 * ʹ�ýӿ���Ϊ��ʵ��Java�Ķ�̬
 */
public interface ProductDAO {
	
	//������Ʒ�ӿ�
	public int insert(Product product);
	//�޸Ĳ�Ʒ�ӿ�
	public int update(Product product);
	//ɾ����Ʒ�Ľӿ�
	public int delete(int id);
	//��ȡ���в�Ʒ�ӿ�
	public List<Product> findAll();
	//����id��ȡ������Ʒ��Ϣ
	public Product findById(int id);
	
	//���Ը�����Ŀ����Ҫ������µĽӿ�
	//���ݲ�Ʒ�����ƣ�ģ����ѯ
	public List<Product> findByProductName(String productName);
	//���ݼ۸��ѯ��Ʒ
	public List<Product> findByProductPrice(double lowPrice, double highPrice);
	
	//��ҳ��ѯ
	public List<Product> findAllByPage(int start,int pageSize);
	
	//ͳ�����м�¼����
	public int count();
	

}
