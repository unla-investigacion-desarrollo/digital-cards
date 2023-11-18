package com.api.unlatestcareer.models;

import java.time.LocalDate;
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
	private byte[] photo;
	private boolean current;
	private List<TitleModel> titles;
	private List<CareerModel> careers;
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
}
