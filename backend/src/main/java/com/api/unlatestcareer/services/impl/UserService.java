package com.api.unlatestcareer.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.unlatestcareer.entities.User;
import com.api.unlatestcareer.exception.CustomNotFoundException;
import com.api.unlatestcareer.helpers.ViewRouteHelper;
import com.api.unlatestcareer.models.UserModel;
import com.api.unlatestcareer.models.UserView;
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
	public UserModel findByName(String username) {
		User user = userRepository.findByUsername(username).get();
		UserModel userModel = mapper.map(user, UserModel.class);
		if (userModel != null) {
			return userModel;
		}
		return null;
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
						user.isEnabled(), user.getUpdateAt(), user.getCreatedAt());
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
			if (userDetails.getPassword()!=null) {
				if (encoder.matches(request.getPassword(), userDetails.getPassword()) ) {
					String token = jwtService.generateToken(userDetails.getUsername());
					response.setUsername(userDetails.getUsername());
					response.setCreatedAt(username.getCreatedAt());
					response.setUpdateAt(username.getUpdateAt());
					response.setToken(token);
				}					
			}
		} catch (Exception e) {
			System.out.println("System Error: " + e.getMessage());
		}
		return response;
	}
}

