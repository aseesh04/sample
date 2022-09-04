package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jwFilter.JwtFilter;
//import org.springframework.security.core.userdetails.UserDetailsService;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsService userDetailService;
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService);
//		auth.inMemoryAuthentication()
//		.withUser("tom")
//		.password(getPasswordEncoder().encode("tom123")).roles("USER")
//		.and()
//		.withUser("jerry").password(getPasswordEncoder().encode("tom123")).roles("ADMIN");
}
	//@Autowired
	//private JwtFilter jwtFilter;
@Override
protected void configure(HttpSecurity http) throws Exception {
http.csrf().disable();
	http.authorizeRequests()
.antMatchers("/admin").hasRole("ADMIN")
.antMatchers("/user").hasRole("USER")
.antMatchers("/authenticate","/token/validate").permitAll()
.and()
//.exceptionHandling().and()
.sessionManagement()
//.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
.and().formLogin();
//http.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
}
@Bean
public AuthenticationManager getAuthenticationManager() throws Exception{
	return super.authenticationManager();
}
@Bean
public PasswordEncoder getPasswordEncoder() {
	return NoOpPasswordEncoder.getInstance();
}
}
