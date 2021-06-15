package com.etcxm.www.entity;

import java.io.Serializable;

// µÃÂ¿‡
public class Product implements Serializable {
	
	private Integer id;
	private String productName;
	private Double productPrice;
	private Integer productStore;
	private String productPdate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getProductStore() {
		return productStore;
	}
	public void setProductStore(Integer productStore) {
		this.productStore = productStore;
	}
	public String getProductPdate() {
		return productPdate;
	}
	public void setProductPdate(String productPdate) {
		this.productPdate = productPdate;
	}
	public Product() {
		super();
	}
	public Product(Integer id, String productName, Double productPrice, Integer productStore, String productPdate) {
		super();
		this.id = id;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productStore = productStore;
		this.productPdate = productPdate;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productStore=" + productStore + ", productPdate=" + productPdate + "]";
	}
	
	
	
}
