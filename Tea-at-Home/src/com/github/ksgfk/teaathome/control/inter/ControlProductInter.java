package com.github.ksgfk.teaathome.control.inter;

import com.github.ksgfk.teaathome.models.Product;

import java.util.List;

public interface ControlProductInter {
	public boolean add(Product product);
	public boolean deleteId(int id);
	public boolean updata(Product product);
	public Product findid( int id);
	public List<Product> findname(String name);
	public List<Product> findall();
	public List<Product> findBatch(int[] productid);
	}