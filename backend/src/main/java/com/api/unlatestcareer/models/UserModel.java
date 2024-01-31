package com.api.unlatestcareer.models;

import java.time.LocalDate;
import java.util.Set;

import com.api.unlatestcareer.entities.Profile;

import lombok.Data;

@Data
public class UserModel {
	private int id;
	private String username;
	private String role;
	private String password;
	private boolean enabled;
	private Set<Profile> profiles;
}
