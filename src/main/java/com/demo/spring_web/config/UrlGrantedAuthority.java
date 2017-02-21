package com.demo.spring_web.config;

import org.springframework.security.core.GrantedAuthority;
/**
 * 自定义GrantedAuthority实现
 * 相当于实现自己的SimpleGrantedAuthority，相当授予某个权限
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class UrlGrantedAuthority implements GrantedAuthority {
	private final String httpMethod;
	private final String url;
	
	public UrlGrantedAuthority(String httpMethod, String url){
		this.httpMethod = httpMethod;
		this.url = url;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return url;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = httpMethod != null ? httpMethod.hashCode() : 0;
		result = 31 * result + (url != null ? url.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		UrlGrantedAuthority target = (UrlGrantedAuthority) obj;
		if(httpMethod.equals(target.getHttpMethod()) && url.equals(target.getUrl())) return true;
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[httpMethod=" + httpMethod + ", url=" + url + "]";
	}

}
