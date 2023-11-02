package com.api.unlatestcareer.services;

import java.util.List;

import com.api.unlatestcareer.models.ProfileModel;

public interface IProfileService {

	ProfileModel findById(int id);

	ProfileModel findByName(String name);

	List<ProfileModel> getAll();

	boolean deleteById(int id);

	ProfileModel save(ProfileModel profile);

	ProfileModel update(ProfileModel profile, int profileId);

	ProfileModel addTitleToProfile(int profileId, int titleId);

	ProfileModel removeTitleFromProfile(int profileId, int titleId);

	ProfileModel addCareerToProfile(int profileId, int careerId);

	ProfileModel removeCareerFromProfile(int profileId, int careerId);

}
