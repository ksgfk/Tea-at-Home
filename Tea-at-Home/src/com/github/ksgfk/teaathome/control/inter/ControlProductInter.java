package com.github.ksgfk.teaathome.control.inter;

import java.util.List;

import www.models.Product;

public interface ControlProductInter {
	public boolean add(Product product);
	public boolean deleteId(int id);
	public boolean updata(Product product);
	public Product findid( int id);
	public List<Product> findname(String name);
}
