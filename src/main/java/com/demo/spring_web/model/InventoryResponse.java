package com.demo.spring_web.model;

import java.io.Serializable;

public class InventoryResponse implements Serializable{
	private String orderId;
	private int returnCode;
	private String comment;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		final int prime = 31;
		int result = 1;
		result = prime*result + (orderId == null ? 0 : orderId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		InventoryResponse inventoryResponse = (InventoryResponse)obj;
		if(orderId == null && inventoryResponse.getOrderId() != null) return false;
		if(!orderId.equals(inventoryResponse.getOrderId())) return false;
		return true;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "InventoryResponse [orderId=" + orderId + ", returnCode=" + returnCode +
				", comment=" + comment +"]";
	}
}
