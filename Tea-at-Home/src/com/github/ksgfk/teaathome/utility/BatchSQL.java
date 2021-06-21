package com.github.ksgfk.teaathome.utility;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.github.ksgfk.teaathome.models.BuyInfo;
import com.github.ksgfk.teaathome.models.ShoppingCart;

public class BatchSQL {
	public  static void set(ShoppingCart item,PreparedStatement pre) throws SQLException {
		pre.setObject(1, item.getId());
	}
	public  static void set(BuyInfo item,PreparedStatement pre) throws SQLException {
		pre.setObject(1, item.getUserId());
		pre.setObject(2, item.getProductId());
		pre.setObject(3, item.getReceive());
		pre.setObject(4, item.getLogistics());
		pre.setObject(5, item.getState());
		pre.setObject(6, item.getPay());


	}
}
