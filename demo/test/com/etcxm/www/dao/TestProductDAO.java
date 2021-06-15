package com.etcxm.www.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.etcxm.www.dao.impl.ProductDAOImpl;
import com.etcxm.www.entity.Product;

public class TestProductDAO {
	
	private ProductDAO productDAO;
	
	@Before		//��ע�������test����֮ǰִ��
	public void init(){
		productDAO=new ProductDAOImpl();		//��=���Oracle ��=����Mysql--->Spring
	}
	
	@Test	//��ע�⣬�����˸÷����Ǹ���Ԫ��������
	public void testFindAll(){
		List<Product> list = productDAO.findAll();
		for(Product product:list){
			System.out.println(product);
		}
	}
	
	//������Ʒ����
	@Test
	public void testInsert(){
		//ģ��һ���µĲ�Ʒ����
		Product product=new Product(null, "Switch", 2400d, 100, "2020-05-31");
		int i=productDAO.insert(product);
		Assert.assertEquals(1, i);
	}
	
	//���²�Ʒ����
	@Test
	public void testUpdate(){
		//��ȡ������¼
		Product product=productDAO.findById(2);
		product.setProductPrice(2500d);		//�޸ĸü�¼�ļ۸�
		int i=productDAO.update(product);	//�ύ����
		Assert.assertEquals(1, i);		//���Խ��Ϊ1
	}
	
	//ɾ����Ʒ����
	@Test
	public void testDelete(){
		int i=productDAO.delete(2);
		Assert.assertEquals(1, i);
	}
	
	//ģ����ѯ����
	@Test
	public void testFindByProductName(){
		String name="p";
		List<Product> list = productDAO.findByProductName(name);
		for(Product product:list){
			System.out.println(product);
		}
	}
	
	@Test
	public void testCount(){
		int i=productDAO.count();
		Assert.assertEquals(i, 9);
	}

}
