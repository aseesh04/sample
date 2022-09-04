package com.example.demo.Dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
private String username;
private String password;
private String role;
}
