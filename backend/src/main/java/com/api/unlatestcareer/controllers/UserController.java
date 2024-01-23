package com.api.unlatestcareer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.unlatestcareer.entities.User;
import com.api.unlatestcareer.exception.CustomNotFoundException;
import com.api.unlatestcareer.helpers.ViewRouteHelper;
import com.api.unlatestcareer.models.UserModel;
import com.api.unlatestcareer.models.UserView;
import com.api.unlatestcareer.security.JwtTokenUtil;
import com.api.unlatestcareer.services.impl.UserService;

@RestController
@RequestMapping(path = "/usuario")
public class UserController {

	private UserService userService;

	@Autowired
	JwtTokenUtil jwtService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("")
	public ResponseEntity<?> createUser(@RequestBody UserModel model) {
		try {
			UserModel savedUser = userService.save(model);
			if (savedUser != null) {
				return ResponseEntity.status(HttpStatus.OK).body(savedUser);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_CREATE);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ViewRouteHelper.ERROR_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserModel model) {
		try {
			UserModel updatedUser = userService.update(model, id);
			if (updatedUser != null) {
				return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ViewRouteHelper.ERROR_NOTFOUND);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_SERVER);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable int id) {
		try {
		/* Revisar para obtener los APPROVED	
		 * UserModel user = userService.findById(id);
			user.getProfiles().stream().filter(p->p.getStatus()== ProfileStatus.APPROVED); */
			//Con esto obtengo el usuario logueado...
			//String userAux = SecurityContextHolder.getContext().getAuthentication().getName();
	
			return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ViewRouteHelper.ERROR_NOTFOUND);
		}
	}

	
	@GetMapping("")
	public ResponseEntity<?> getAllUsers() {
		try {
			List<UserModel> users = userService.getAll();
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_SERVER);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id) {
		try {
			boolean deleted = userService.deleteById(id);
			if (deleted) {
				return ResponseEntity.status(HttpStatus.OK).body(ViewRouteHelper.SUCCESS_DELETE);
			} else {
				throw new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND);
			}
		} catch (CustomNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_SERVER);
		}
	}

	@PostMapping("/login")
	public UserView autenticarUsuario(@RequestBody User request) {
		return userService.userAuthenticate(request);
	}
	
	@PostMapping("/{userName}/profiles/{profileId}")
	public ResponseEntity<?> addProfileToUser(@PathVariable String username, @PathVariable int profileId) {
		try {
			UserModel userModel = userService.addProfileToUser(username, profileId);
			return ResponseEntity.status(HttpStatus.OK).body(userModel);
		} catch (CustomNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_SERVER);
		}
	}

	@DeleteMapping("/{userId}/profiles/{profileId}")
	public ResponseEntity<?> removeProfileToUser(@PathVariable int userId, @PathVariable int profileId) {
		try {
			UserModel userModel = userService.removeProfileFromUser(userId, profileId);
			return ResponseEntity.status(HttpStatus.OK).body(userModel);
		} catch (CustomNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_SERVER);
		}
	}
	
	
	
	}
