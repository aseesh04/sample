package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class UserEntity {
	@Id
	private int id;
	private String username;
	private String password;
	private String role;

}
