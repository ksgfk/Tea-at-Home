package com.github.ksgfk.teaathome.models;

import java.util.ArrayList;
import java.util.List;

public class Userdata {
	private List<Shopping_cart> car=null;
	private int user_id=0;
	private int permission=0;
	public Userdata(List<Shopping_cart> car, int userid) {
		this.car=car;
		this.user_id=userid;
	}

	public List<Shopping_cart> getCar() {
		return car;
	}
	
	public void setCar(List<Shopping_cart> car) {
		this.car= new ArrayList<Shopping_cart>();
		for(int i=0;i<car.size();i++) {
			car.add(new Shopping_cart(car.get(i)));
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
