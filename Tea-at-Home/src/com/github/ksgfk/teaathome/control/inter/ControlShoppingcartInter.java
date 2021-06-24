package com.github.ksgfk.teaathome.control.inter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.ksgfk.teaathome.models.ShoppingCart;
import com.github.ksgfk.teaathome.utility.Pair;

public interface ControlShoppingcartInter {
	public boolean add(ShoppingCart cart);
	public boolean delete(int id);
	public List<ShoppingCart> finduserid(int userid);
	public Map<Pair<String,Double>, ShoppingCart> finduserIdName(int userid);
	public ShoppingCart findid(int id);
	public boolean updata(ShoppingCart cart);
	public ShoppingCart find(int userid,int productid);
	public boolean deletebatch(List<ShoppingCart> list);
	public  Map<String, ShoppingCart> querybatch(int[] productid,int userid) ;
	//public 	Map<String,ShoppingCart> query(int[] productid,int uesrid);
}
