package com.github.ksgfk.teaathome.control.inter;

import www.models.Depository;
import www.models.Product;

public interface ControlDepositoryInter {
	public boolean add(Depository depository);
	public boolean delete(int id);
	public boolean updata(Depository depository);
	public Depository findid(int id);
	public boolean addProduct(Product product,int id);
	public boolean deleteProduct(Product product);
	
}
