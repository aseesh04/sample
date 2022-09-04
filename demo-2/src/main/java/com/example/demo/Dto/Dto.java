package com.example.demo.Dto;






import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString



public class Dto {
	private int id;
	private String email;
	private String password;
	private String JwtToken;
	public void setJwtToken(String JwtToken) {
		
		this.JwtToken=JwtToken;
	}
	public String getJwtToken(String JwtToken) {
		return JwtToken;
	}
	

}
