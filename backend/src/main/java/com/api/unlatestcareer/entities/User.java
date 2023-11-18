package com.api.unlatestcareer.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.api.unlatestcareer.models.UserModel;
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

	private String username;
	private String role;
	private String password;
	private boolean enabled;
	private LocalDate createdAt;
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
