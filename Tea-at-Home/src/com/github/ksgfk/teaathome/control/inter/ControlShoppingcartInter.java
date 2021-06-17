package com.github.ksgfk.teaathome.control.inter;

import com.github.ksgfk.teaathome.models.ShoppingCart;

public interface ControlShoppingcartInter {
	public boolean add(ShoppingCart cart);
	public boolean delete(int id);
	public ShoppingCart finduserid(int userid);
	public ShoppingCart findid(int id);
	public boolean updata(ShoppingCart cart);
}
