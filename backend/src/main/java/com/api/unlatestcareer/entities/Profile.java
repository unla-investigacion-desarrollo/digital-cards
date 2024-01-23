package com.api.unlatestcareer.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.api.unlatestcareer.helpers.ProfileStatus;
import com.api.unlatestcareer.models.ProfileModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "profile")
@AllArgsConstructor
public class Profile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private byte[] photo;
	private boolean current;

	private String title;

	@ManyToMany
	@JoinTable(name = "profile_career", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "career_id", nullable = true))
	List<Career> careers;

	private ProfileStatus status;
	private String courses;
	private String name;
	private String lastname;
	private String urlLinkedin;
	private String mail;
	private String phone;
	private String moreInfo;
	private LocalDate createdAt;
	private LocalDate updateAt;

	public Profile(ProfileModel profile) {
		this.id = profile.getId();
		this.photo = profile.getPhoto();
		this.current = profile.isCurrent();
		this.title = profile.getTitle();
		this.status = profile.getStatus();
		this.courses = profile.getCourses();
		this.name = profile.getName();
		this.lastname = profile.getLastname();
		this.urlLinkedin = profile.getUrlLinkedin();
		this.mail = profile.getMail();
		this.phone = profile.getPhone();
		this.moreInfo = profile.getMoreInfo();
		this.createdAt = profile.getCreatedAt();
		this.updateAt = profile.getUpdateAt();
	}

	public Profile(byte[] photo, boolean current, String title, ProfileStatus status, String courses, String name,
			String lastname, String urlLinkedin, String mail, String phone, String moreInfo, LocalDate createdAt,
			LocalDate updateAt) {
		this.photo = photo;
		this.current = current;
		this.title = title;
		this.status = status;
		this.courses = courses;
		this.name = name;
		this.lastname = lastname;
		this.urlLinkedin = urlLinkedin;
		this.mail = mail;
		this.phone = phone;
		this.moreInfo = moreInfo;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}
}
