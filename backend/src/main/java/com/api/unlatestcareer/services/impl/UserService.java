package com.api.unlatestcareer.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.api.unlatestcareer.models.ProfileModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.unlatestcareer.entities.Profile;
import com.api.unlatestcareer.entities.User;
import com.api.unlatestcareer.exception.CustomNotFoundException;
import com.api.unlatestcareer.helpers.ViewRouteHelper;
import com.api.unlatestcareer.models.UserModel;
import com.api.unlatestcareer.models.UserView;
import com.api.unlatestcareer.repositories.IProfileRepository;
import com.api.unlatestcareer.repositories.IUserRepository;
import com.api.unlatestcareer.security.JwtTokenUtil;
import com.api.unlatestcareer.security.UserSecurity;
import com.api.unlatestcareer.services.IUserService;

@Service("userService")
public class UserService implements IUserService {

	private ModelMapper mapper = new ModelMapper();

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IProfileRepository profileRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	JwtTokenUtil jwtService;

	@Autowired
	private UserSecurity userSec;

	@Override
	public UserModel findById(int id) {
		try {
			Optional<User> optionalUser = userRepository.findById(id);

			if (optionalUser.isPresent()) {
				User user = optionalUser.get();
				UserModel userModel = mapper.map(user, UserModel.class);
				return userModel;
			} else {
				throw new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND);
			}
		} catch (Exception e) {
			throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
		}
	}

	@Override
	public Profile getCurrentProfileByUserId(int userId) {
		try {
			Profile profile  = userRepository.getCurrentProfileByUserId(userId);

			return profile;
		} catch (Exception e) {
			throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
		}
	}

	public UserModel findByName(String username) {
	    Optional<User> userOptional = userRepository.findByUsername(username);
	    
	    if (userOptional.isPresent()) {
	        User user = userOptional.get();
	        return mapper.map(user, UserModel.class);
	    } else {
	        throw new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND);
	    }
	}


	@Override
	public List<UserModel> getAll() {
		List<User> users = userRepository.findAll();
		return users.stream().map(user -> mapper.map(user, UserModel.class)).collect(Collectors.toList());
	}

	@Override
	public boolean deleteById(int id) {
		try {
			User userExisting = userRepository.findById(id).orElse(null);
			boolean deleted = false;
			if (userExisting != null) {
				userRepository.deleteById(userExisting.getId());
				deleted = true;
				return deleted;
			} else {
				throw new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND);
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public UserModel save(UserModel user) {
		try {
			User userExisting = userRepository.findById(user.getId()).orElse(null);
			if (userExisting == null) {
				userExisting = new User(user.getUsername(), user.getRole(), encoder.encode(user.getPassword()),
						user.isEnabled(), user.getUpdateAt(), user.getCreatedAt(),user.getProfiles());
			} else {
				userExisting = new User(user);
			}
			userRepository.save(userExisting);
			return mapper.map(userExisting, UserModel.class);
		} catch (Exception e) {
			throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
		}
	}

	@Override
	public UserModel update(UserModel user, int userId) {
		try {
			User userExisting = userRepository.findById(userId)
					.orElseThrow(() -> new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND));
			userExisting.setUsername(user.getUsername());
			userExisting.setRole(user.getRole());
			userExisting.setEnabled(user.isEnabled());
			userExisting.setUpdateAt(user.getUpdateAt());
			userExisting.setCreatedAt(user.getCreatedAt());
			userExisting.setProfiles(user.getProfiles());
			if (user.getPassword() != null && !user.getPassword().isEmpty()) {
				userExisting.setPassword(encoder.encode(user.getPassword()));
			}
			userRepository.save(userExisting);
			return mapper.map(userExisting, UserModel.class);
		} catch (Exception e) {
			throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
		}
	}

	@Override
	public UserView userAuthenticate(User request) {
		UserView response = new UserView();
		try {
			UserModel username = this.findByName(request.getUsername());
			UserDetails userDetails = userSec.loadUserByUsername(request.getUsername());
			if (userDetails.getPassword() != null) {
				if (encoder.matches(request.getPassword(), userDetails.getPassword())) {
					String token = jwtService.generateToken(userDetails.getUsername());
					response.setUserId(username.getId());
					response.setUsername(userDetails.getUsername());
					response.setCreatedAt(username.getCreatedAt());
					response.setUpdateAt(username.getUpdateAt());
					response.setToken(token);
				}
			}
			return response;
		} catch (Exception e) {
			throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
		}
	}
	
	public UserModel addProfileToUser(String username, int profileId) {
	    try {
	        User userExisting = userRepository.findByUsername(username)
	                .orElseThrow(() -> new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND));

	        Profile profileExisting = profileRepository.findById(profileId)
	                .orElseThrow(() -> new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND));

	        if (userExisting != null && profileExisting != null) {
	            userExisting.getProfiles().add(profileExisting);
	            userRepository.save(userExisting);
	            System.out.println("Perfil agregado exitosamente al usuario: " + username);
	        }
	        return mapper.map(userExisting, UserModel.class);
	    } catch (Exception e) {
	        System.out.println("Error al agregar perfil al usuario: " + e.getMessage());
	        throw e;
	    }
	}

	
	public UserModel removeProfileFromUser(int userId, int profileId) {
		User userExisting = userRepository.findById(userId)
				.orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));

		Profile profileExisting = profileRepository.findById(profileId)
				.orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));

		if (userExisting != null && profileExisting != null) {
			if (userExisting.getProfiles().contains(profileExisting)) {
				userExisting.getProfiles().remove(profileExisting);
			}
			userRepository.save(userExisting);
		}

		return mapper.map(userExisting, UserModel.class);
	}

	
	}
