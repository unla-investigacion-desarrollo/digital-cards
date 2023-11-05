package com.api.unlatestcareer.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.api.unlatestcareer.models.UserModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Column(name="username")
	private String username;

	@Column(name="role")
	private String role;

	@Column(name="password")
	private String password;

	@Column(nullable = true)
	private boolean enabled;

	@Column(nullable = true)
	private LocalDate createdAt;

	@Column(nullable = true)
	private LocalDate updateAt;
	
	public User(UserModel user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.role = user.getRole();
		this.password = user.getPassword();
		this.enabled = user.isEnabled();
		this.createdAt = user.getCreatedAt();
		this.updateAt = user.getUpdateAt();
	}

	public User(String username, String role, String password, boolean enabled, LocalDate createdAt,
			LocalDate updateAt) {
		super();
		this.username = username;
		this.role = role;
		this.password = password;
		this.enabled = enabled;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}
}
