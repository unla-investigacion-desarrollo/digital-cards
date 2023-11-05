package com.api.unlatestcareer.models;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserModel {
	private int id;
	private String username;
	private String role;
	private String password;
	private boolean enabled;
	private LocalDate createdAt;
	private LocalDate updateAt;
}
