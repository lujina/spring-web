package com.demo.spring_web.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * spring安全配置
 * 用户登陆控制、remember-me功能，以及权限控制
 * 
 * @author Administrator
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	@Qualifier("CustomUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	PersistentTokenRepository tokenRepository;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		// auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/res/**", "/back/**", "/register.html").permitAll()
				.antMatchers("/mailbox.html").access("hasRole('ADMIN')")
				.anyRequest().hasRole("USER").anyRequest().authenticated().and().exceptionHandling()
				.accessDeniedPage("/Access_Denied");
		http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").defaultSuccessUrl("/login/success", true)
				.failureUrl("/login/error").permitAll().and().rememberMe().rememberMeParameter("remember_me")
				.tokenRepository(tokenRepository).tokenValiditySeconds(86400).and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).logoutSuccessUrl("/login.html")
				.permitAll();
		http.csrf().ignoringAntMatchers("/back/**", "/login");

	}
/*
 * 可以使用BCrypt对密码进行加密，但前提需要你保存在数据库的密码是经过BCrypt加密的
 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	// @Bean
	// public PersistentTokenBasedRememberMeServices
	// getPersistentTokenBasedRememberMeServices() {
	// PersistentTokenBasedRememberMeServices tokenBasedservice = new
	// PersistentTokenBasedRememberMeServices(
	// "remember_me", userDetailsService, tokenRepository);
	// return tokenBasedservice;
	// }

	// @Bean
	// public AuthenticationTrustResolver getAuthenticationTrustResolver() {
	// return new AuthenticationTrustResolverImpl();
	// }
}