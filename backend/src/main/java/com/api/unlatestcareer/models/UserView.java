package com.api.unlatestcareer.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserView {
	private String username;
	private String token;
	private int userId;
	private String role;
}
