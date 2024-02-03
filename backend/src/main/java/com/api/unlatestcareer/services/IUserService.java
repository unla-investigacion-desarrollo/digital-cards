package com.api.unlatestcareer.services;

import java.util.List;


import com.api.unlatestcareer.entities.Profile;
import com.api.unlatestcareer.entities.User;
import com.api.unlatestcareer.models.UserModel;
import com.api.unlatestcareer.models.UserView;

public interface IUserService {
	UserModel findById(int id);

	Profile getCurrentProfileByUserId(int userId);

	UserModel findByName(String username);

	List<UserModel> getAll();

	boolean deleteById(int id);
	
	UserModel save(UserModel user);
	
	UserModel update(UserModel user, int userId);

	UserView userAuthenticate(User request);	

	void setLastProfileFalse (User user);
}
