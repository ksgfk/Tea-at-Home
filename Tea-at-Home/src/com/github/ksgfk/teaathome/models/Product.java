package com.github.ksgfk.teaathome.models;

public class Product {
	private int id;
	private String name;
	private int count;
	private int depositoryId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getDepositoryId() {
		return depositoryId;
	}
	public void setDepositoryId(int depositoryId) {
		this.depositoryId = depositoryId;
	}
	public Product(int id, String name, int count, int depositoryId) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
		this.depositoryId = depositoryId;
	}

}
