package com.demo.spring_web.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
/**
 * 自定义ConfigAttribute的实现
 * @author Administrator
 *
 */
public class UrlConfigAttribute implements ConfigAttribute {
	private final HttpServletRequest request;
	public UrlConfigAttribute(HttpServletRequest request){
		this.request = request;
	}
	@Override
	public String getAttribute() {
		// TODO Auto-generated method stub
		return null;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
}
