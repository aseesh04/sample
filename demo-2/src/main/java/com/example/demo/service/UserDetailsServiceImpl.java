package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repo.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
@Autowired
UserRepo userRepo;
@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		List<UserEntity> userEntityList=userRepo.findByUsername(username);
		if(userEntityList==null||userEntityList.size()==0) {
			throw new UsernameNotFoundException(username);
		}
		UserEntity userEntity=userEntityList.get(0);
		List<GrantedAuthority> authorities=new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority(userEntity.getRole()));
		User user=new User(userEntity.getUsername(),userEntity.getPassword(),authorities);
		return user;
	}

	
}
