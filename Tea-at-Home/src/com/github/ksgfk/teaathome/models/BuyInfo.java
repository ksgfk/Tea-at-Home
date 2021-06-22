package com.github.ksgfk.teaathome.models;

public class BuyInfo implements Comparable<BuyInfo> {
	private int id;
	private int userId;
	private int productId;
	private String receive;
	private String logistics;
	private int state;
	private double pay;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getReceive() {
		return receive;
	}
	public void setReceive(String receive) {
		this.receive = receive;
	}
	public String getLogistics() {
		return logistics;
	}
	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public double getPay() {
		return pay;
	}
	public void setPay(double pay) {
		this.pay = pay;
	}
	public BuyInfo(int id, int userId, int productId, String receive, String logistics, int state, double pay) {
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.receive = receive;
		this.logistics = logistics;
		this.state = state;
		this.pay = pay;
	}
	@Override
	public int compareTo(BuyInfo o) {
		if(userId==o.userId) {
			return productId<o.productId?1:-1;
		}
		return -1;
	}
	
	
	
}
