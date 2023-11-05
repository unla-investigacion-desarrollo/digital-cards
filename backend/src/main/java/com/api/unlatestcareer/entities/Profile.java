package com.api.unlatestcareer.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.api.unlatestcareer.helpers.ProfileStatus;
import com.api.unlatestcareer.models.ProfileModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="profile")
public class Profile implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="photo")
	private byte[] photo;
	
	@Column(name="current")
	private boolean current;
	
	@ManyToMany
	@JoinTable(
		name="profile_title",
		joinColumns = @JoinColumn (name="profile_id"),
		inverseJoinColumns = @JoinColumn (name="title_id")
	)
	List<Title> titles;
	
	@ManyToMany
	@JoinTable(
			name="profile_career",
			joinColumns = @JoinColumn (name="profile_id"),
			inverseJoinColumns = @JoinColumn (name="career_id")
		)
	List<Career> careers;
	
	@Column(name="status")
	private ProfileStatus status;
	
	@Column(name="courses")
	private String courses;
	
	@Column(name="name")
	private String name;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="urlLinkedin")
	private String urlLinkedin;
	
	@Column(name="mail")
	private String mail;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="moreInfo")
	private String moreInfo;
	
	@Column(name="createdAt")
	private LocalDate createdAt;
	
	@Column(name="updateAt")
	private LocalDate updateAt;
	
	public Profile(ProfileModel profile) {
		this.id = profile.getId();
		this.photo = profile.getPhoto();
		this.current = profile.isCurrent();
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

	public Profile(byte[] photo, boolean current, ProfileStatus status, String courses, String name,
			String lastname, String urlLinkedin, String mail, String phone, String moreInfo, LocalDate createdAt,
			LocalDate updateAt) {
		this.photo = photo;
		this.current = current;
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
