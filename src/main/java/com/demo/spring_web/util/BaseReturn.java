package com.demo.spring_web.util;

import java.io.Serializable;

public class BaseReturn implements Serializable {
	/**
	 * 返回客户端统一格式，包括状态码，提示信息，以及业务数据
	 */
	private static final long serialVersionUID = 1L;
	//状态码
	private int status;
	//必要的提示信息
	private String msg;
	//业务数据
	private Object data;
	
	public BaseReturn(int status,String msg, Object data){
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
