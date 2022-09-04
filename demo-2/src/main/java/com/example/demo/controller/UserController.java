package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

//import java.util.ArrayList;
//import java.util.List;
import com.example.demo.service.UserDetailsServiceImpl.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.AuthenticationRequest;
import com.example.demo.entity.UserEntity;
//import com.example.demo.Dto.Dto;
//import com.example.demo.service.UserDetailsServiceImpl;
//import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtils;

//import com.example.demo.dto.Dto;



@RestController
//@RequestMapping("/home")
public class UserController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	JwtUtils jwtUtils;
//	@GetMapping(value="token/validate")
//	public ResponseEntity<String> isTokenValid(@RequestHeader("Authorization") String jwtToken){
//		jwtToken=jwtToken.substring(7,jwtToken.length());
//		String username=jwtUtils.extractUsername(jwtToken);
//		//UserDetails userDetails=UserDetailsService.loadUserByUsername(username);
//		UserDetails userDetails=userDetailsService.loadUserByUsername(username);
//		boolean isTokenValid=jwtUtils.validateToken(jwtToken, userDetails);
//		return new ResponseEntity<String>(username,HttpStatus.OK);
//		
//	}
	
	@PostMapping(value="/authenticate",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<auth> authenticate(@RequestBody AuthenticationRequest authRequest){
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
					);
		}
		catch(BadCredentialsException e) {
			throw new BadCredentialsException("User is not exist");
		}
		
	String jwtToken=jwtUtils.generateToken(authRequest.getUsername());
	
	
	String username=jwtUtils.extractUsername(jwtToken);
	
	//UserDetails userDetails=userDetailsService.loadUserByUsername(username);
	UserDetails userDetails=userDetailsService.loadUserByUsername(username);
	String role=userDetails.getAuthorities().toString();
	auth list=new auth();
	list.setToken(jwtToken);
	list.setRole(role);
		return new ResponseEntity<auth>(list,HttpStatus.OK);
		
	}
	
	@GetMapping("/user")
	public String User() {
		return "User";
	}
	@GetMapping("/admin")
	public String Admin() {
		return "Admin";
	}
//	
}
