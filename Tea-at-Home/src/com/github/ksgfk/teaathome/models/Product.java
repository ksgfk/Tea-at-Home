package com.github.ksgfk.teaathome.models;

import java.math.BigDecimal;

public class Product {
	private int id;
	private String name;
	private int count;
	private int depositoryId;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Product(int id, String name, int count, int depositoryId, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.count = count;
		this.depositoryId = depositoryId;
		this.price = price;
	}

	private BigDecimal price;
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

}
