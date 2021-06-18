package com.github.ksgfk.teaathome.control.inter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.ksgfk.teaathome.models.ShoppingCart;

public interface ControlShoppingcartInter {
	public boolean add(ShoppingCart cart);
	public boolean delete(int id);
	public List<ShoppingCart> finduserid(int userid);
	public ShoppingCart findid(int id);
	public boolean updata(ShoppingCart cart);
	public ShoppingCart find(int userid,int productid);

}
