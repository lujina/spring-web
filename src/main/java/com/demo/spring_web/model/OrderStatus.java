package com.demo.spring_web.model;

public enum OrderStatus {
	CREATED("Created"),
	PENDING("Pending"),
	CONFIRMED("Confirmed"),
	FAILED("Failed");
	private String status;
	private OrderStatus(final String status){
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.status;
	}
	public String getName(){
		return this.name();
	}
}
