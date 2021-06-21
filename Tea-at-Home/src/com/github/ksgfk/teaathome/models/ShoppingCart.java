package com.github.ksgfk.teaathome.models;

public class ShoppingCart {
	private int id;
	private int userId;
	private int productId;
	private int count;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ShoppingCart(int id, int userId, int productId, int count) {
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.count = count;
	}
	public ShoppingCart(ShoppingCart c) {
		this.id = c.id;
		this.userId = c.userId;
		this.productId = c.productId;
		this.count = c.count;
	}
	public String toString() {
		return Integer.toString(id)+","+Integer.toString(userId)+"'"+Integer.toString(productId)+","+Integer.toString(count);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + id;
		result = prime * result + productId;
		result = prime * result + userId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCart other = (ShoppingCart) obj;
		if (count != other.count)
			return false;
		if (productId != other.productId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
}
