package com.etcxm.www.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.etcxm.www.entity.Product;
import com.etcxm.www.service.impl.ProductServiceImpl;



public class TestProductService {
	
	private ProductService productService;
	
	@Before
	public void init(){
		productService=new ProductServiceImpl();
	}
	
	//1. 必须是public
	//2. 不传参数
	@Test
	public void testSave(){
		
		Product product=new Product(null, "switch", 2500d,50, "2021-06-04");
		boolean isok = productService.save(product);
		Assert.assertTrue(isok);
		
	}
	
	@Test
	public void testFindAll(){
		
		List<Product> list = productService.findAll();
		for(Product product:list){
			System.out.println(product);
		}
		
	}
	
	@Test
	public void testUpdate(){
		System.out.println(productService);
		Product product = productService.findById(6);
		System.out.println(product);
		product.setProductPdate("2021-05-01");
		product.setProductPrice(2600d);
		product.setProductStore(49);
		
		boolean isok = productService.update(product);
		Assert.assertTrue(isok);
		
	}
	
	@Test
	public void testFindByName(){
		
		String name="ch";
		List<Product> list = productService.findByProductName(name);
		for(Product product:list){
			System.out.println(product);
		}
		
	}
	
	@Test
	public void testDelete(){
		
		boolean isok = productService.delete(3);
		Assert.assertTrue(isok);
		
	}
	
	@Test
	public void testFindByProductPrice(){
		
		List<Product> list = productService.findByProductPrice(1000, 1500);	//包含1000和1500
		for(Product product:list){
			System.out.println(product);
		}
		
	}
	
	
	

}
