package com.demo.spring_web.model;

import java.io.Serializable;

import javax.persistence.Entity;

public class Order implements Serializable{
	private String id;
	private String productName;
	private int quantity;
	private OrderStatus status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		final int prime = 31;
		int result = 1;
		result = prime*result + (id == null ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		Order order = (Order)obj;
		if(id == null && order.getId() != null) return false;
		if(!id.equals(order.getId())) return false;
		return true;
	}
}
