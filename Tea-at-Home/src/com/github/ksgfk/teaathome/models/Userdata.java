package com.github.ksgfk.teaathome.models;

import java.util.ArrayList;
import java.util.List;

public class Userdata {
	private List<ShoppingCart> car=null;
	private int user_id=0;
	private int permission=0;
	public Userdata(List<ShoppingCart> car, int userid) {
		this.car=car;
		this.user_id=userid;
	}

	public List<ShoppingCart> getCar() {
		return car;
	}
	
	public void setCar(List<ShoppingCart> car) {
		this.car= new ArrayList<ShoppingCart>();
		for(int i=0;i<car.size();i++) {
			ShoppingCart c =car.get(i);
			car.add(new ShoppingCart(c));
		}
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}
	
	
}
