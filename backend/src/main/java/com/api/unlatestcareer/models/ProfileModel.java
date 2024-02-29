package com.api.unlatestcareer.models;

import java.util.List;

import com.api.unlatestcareer.helpers.ProfileStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileModel {
	private int id;
	private String photo;
	private boolean current;
	private String title;
	private int idCareer;
	private ProfileStatus status;
	private List<String> courses;
	private List<String> institutions;
	private String name;
	private String projects;
	private String urlLinkedin;
	private String mail;
	private String phone;
	private String moreInfo;
}
