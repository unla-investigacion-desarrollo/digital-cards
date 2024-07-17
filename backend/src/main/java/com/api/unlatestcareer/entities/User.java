package com.api.unlatestcareer.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import com.api.unlatestcareer.models.UserModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User extends BaseEntityAudit {
	private static final long serialVersionUID = 1L;

	private String username;
	private String role;
	private String password;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
	private Set<Profile> profiles;
	
	public User(UserModel user) {
		this.setId(user.getId());
		this.username = user.getUsername();
		this.role = user.getRole();
		this.password = user.getPassword();
		this.setEnabled(user.isEnabled());
		this.profiles = user.getProfiles();
	}

	public User(String username, String role, String password, boolean enabled, Set<Profile> profiles) {
		super();
		this.username = username;
		this.role = role;
		this.password = password;
		this.setEnabled(enabled);
		this.profiles = profiles;
	}
}
