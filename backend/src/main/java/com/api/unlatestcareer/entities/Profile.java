package com.api.unlatestcareer.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.api.unlatestcareer.helpers.ProfileStatus;
import com.api.unlatestcareer.models.ProfileModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "profile")
@AllArgsConstructor
public class Profile  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String photo;
	private boolean current;

	private String title;

	@ManyToMany
	@JoinTable(name = "profile_career", joinColumns = @JoinColumn(name = "profile_id"),
			   inverseJoinColumns = @JoinColumn(name = "career_id", nullable = true))
	List<Career> careers;

	private ProfileStatus status;
	private List<String> courses;
	private String name;
	private String projects;
	private String urlLinkedin;
	private String mail;
	private String phone;
	private String moreInfo;
	private List<String> institutions;
	private LocalDate createdAt;
	private LocalDate updateAt;



	public Profile(ProfileModel profile) {
		this.id = profile.getId();
		this.photo = profile.getPhoto();
		this.current = profile.isCurrent();
		this.title = profile.getTitle();
		this.status = ProfileStatus.PENDING;
		this.courses = profile.getCourses();
		this.name = profile.getName();
		this.urlLinkedin = profile.getUrlLinkedin();
		this.mail = profile.getMail();
		this.phone = profile.getPhone();
		this.institutions = profile.getInstitutions();
		this.moreInfo = profile.getMoreInfo();
		this.createdAt = LocalDate.now();
		this.updateAt = LocalDate.now();
		this.projects = profile.getProjects();
	}

	public Profile(String photo, boolean current, String title, ProfileStatus status, List<String> courses,List<String> institutions, String name,
			 String urlLinkedin, String mail, String phone, String moreInfo,String projects) {
		this.photo = photo;
		this.current = current;
		this.title = title;
		this.status = ProfileStatus.PENDING;
		this.courses = courses;
		this.name = name;
		this.urlLinkedin = urlLinkedin;
		this.mail = mail;
		this.phone = phone;
		this.moreInfo = moreInfo;
		this.institutions = institutions;
		this.createdAt = LocalDate.now();
		this.updateAt = LocalDate.now();
		this.careers = new ArrayList<>();
		this.projects = projects;
	}
}
