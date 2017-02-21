package com.demo.spring_web.config;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 自定义voter的实现
 * @author Administrator
 *
 */
public class UrlMatchVoter implements AccessDecisionVoter<Object> {

	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		if(attribute instanceof UrlConfigAttribute) return true;
		return false;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
		// TODO Auto-generated method stub
		if(authentication == null) return ACCESS_DENIED;
		Collection<? extends GrantedAuthority> authorities  = authentication.getAuthorities();
		System.out.println("authorities.size():" + authorities.size());
		for(ConfigAttribute attribute : attributes){
			if(!(attribute instanceof UrlConfigAttribute)) continue;
			UrlConfigAttribute urlConfigAttribute = (UrlConfigAttribute) attribute;
			for(GrantedAuthority authority : authorities){
				if(!(authority instanceof UrlGrantedAuthority)) continue;
				UrlGrantedAuthority urlGrantedAuthority = (UrlGrantedAuthority) authority;
				if(urlGrantedAuthority.getAuthority() != null){
					//如果数据库的method字段为null，则默认所有方法都支持
					String httpMethod = (urlGrantedAuthority.getHttpMethod() != null) ? urlGrantedAuthority.getHttpMethod()
	                        : urlConfigAttribute.getRequest().getMethod();
	                //用Spring已经实现的AntPathRequestMatcher进行匹配，这样我们数据库中的url也就支持ant风格的配置了（例如：/xxx/user/**）        
	                AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(urlGrantedAuthority.getAuthority(), httpMethod);
	                if (antPathRequestMatcher.matches(urlConfigAttribute.getRequest()))
	                    return ACCESS_GRANTED;
				}
			}
		}
		return ACCESS_ABSTAIN;
	}

}
