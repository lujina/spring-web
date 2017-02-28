package com.demo.spring_web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Privilege implements Serializable{
	@Id
	@GeneratedValue
	private int id;
	private String url;
	private String method;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Privilege [id=" + id + ", url=" + url + ", method=" + method +"]";
	}
}
