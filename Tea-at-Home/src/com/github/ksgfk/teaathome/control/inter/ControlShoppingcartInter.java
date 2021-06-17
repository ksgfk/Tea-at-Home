package com.github.ksgfk.teaathome.control.inter;

import www.models.Shopping_cart;

public interface ControlShoppingcartInter {
	public boolean add(Shopping_cart cart);
	public boolean delete(int id);
	public Shopping_cart finduserid(int userid);
	public Shopping_cart findid(int id);
	public boolean updata(Shopping_cart cart);
}
