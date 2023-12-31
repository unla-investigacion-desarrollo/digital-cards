package com.api.unlatestcareer.controllers;

import java.util.List;

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

import com.api.unlatestcareer.exception.CustomNotFoundException;
import com.api.unlatestcareer.helpers.ViewRouteHelper;
import com.api.unlatestcareer.models.CareerModel;
import com.api.unlatestcareer.models.ProfileModel;
import com.api.unlatestcareer.models.TitleModel;
import com.api.unlatestcareer.services.impl.ProfileService;
import com.api.unlatestcareer.services.impl.UtilService;

@RestController
@RequestMapping(path = "/profiles")
public class ProfileCustomController {

	private ProfileService profileService;

	public ProfileCustomController(ProfileService profileService) {
		this.profileService = profileService;
	}

	@PostMapping("")
	public ResponseEntity<?> createProfile(@RequestBody ProfileModel model) {
		try {
			ProfileModel savedProfile = profileService.save(model);
			if (savedProfile != null) {
				return ResponseEntity.status(HttpStatus.OK).body(savedProfile);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_CREATE);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ViewRouteHelper.ERROR_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateProfile(@PathVariable int id, @RequestBody ProfileModel model) {
		try {
			ProfileModel updatedProfile = profileService.update(model, id);
			if (updatedProfile != null) {
				return ResponseEntity.status(HttpStatus.OK).body(updatedProfile);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ViewRouteHelper.ERROR_NOTFOUND);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_SERVER);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findProfileById(@PathVariable int id) {
		try {
			if (UtilService.hasRole(ViewRouteHelper.ADMIN_ROLE)) {
				ProfileModel profile = profileService.findById(id);
				if (profile != null) {
					return ResponseEntity.status(HttpStatus.OK).body(profile);
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ViewRouteHelper.ERROR_NOTFOUND);
				}
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ViewRouteHelper.ACCESS_DENIED);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ViewRouteHelper.ERROR_NOTFOUND);
		}
	}

	@GetMapping("")
	public ResponseEntity<?> getAllProfiles() {
		try {
			if (UtilService.hasRole(ViewRouteHelper.ADMIN_ROLE)) {
				List<ProfileModel> profiles = profileService.getAll();
				return ResponseEntity.ok(profiles);
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ViewRouteHelper.ACCESS_DENIED);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_SERVER);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProfile(@PathVariable int id) {
		try {
			boolean deleted = profileService.deleteById(id);
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

	@PostMapping("/{profileId}/titles/{titleId}")
	public ResponseEntity<?> addTitleToProfile(@PathVariable int profileId, @PathVariable int titleId) {
		try {
			ProfileModel profileModel = profileService.addTitleToProfile(profileId, titleId);
			return ResponseEntity.status(HttpStatus.OK).body(profileModel);
		} catch (CustomNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_SERVER);
		}
	}

	@DeleteMapping("/{profileId}/titles/{titleId}")
	public ResponseEntity<?> removeTitleFromProfile(@PathVariable int profileId, @PathVariable int titleId) {
		try {
			ProfileModel profileModel = profileService.removeTitleFromProfile(profileId, titleId);
			return ResponseEntity.status(HttpStatus.OK).body(profileModel);
		} catch (CustomNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_SERVER);
		}
	}

	@PostMapping("/{profileId}/careers/{careerId}")
	public ResponseEntity<?> addCareerToProfile(@PathVariable int profileId, @PathVariable int careerId) {
		try {
			ProfileModel profileModel = profileService.addCareerToProfile(profileId, careerId);
			return ResponseEntity.status(HttpStatus.OK).body(profileModel);
		} catch (CustomNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_SERVER);
		}
	}

	@DeleteMapping("/{profileId}/careers/{careerId}")
	public ResponseEntity<?> removeCareerFromProfile(@PathVariable int profileId, @PathVariable int careerId) {
		try {
			ProfileModel profileModel = profileService.removeCareerFromProfile(profileId, careerId);
			return ResponseEntity.status(HttpStatus.OK).body(profileModel);
		} catch (CustomNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_SERVER);
		}
	}

	@PostMapping("/{profileId}/careers")
	public ResponseEntity<?> addCareerToProfile(@PathVariable int profileId, @RequestBody CareerModel careerModel) {
		try {
			ProfileModel profileModel = profileService.addCareerToProfile(profileId, careerModel);
			return ResponseEntity.status(HttpStatus.OK).body(profileModel);
		} catch (CustomNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_SERVER);
		}
	}

	@PostMapping("/{profileId}/titles")
	public ResponseEntity<?> addTitleToProfile(@PathVariable int profileId, @RequestBody TitleModel titleModel) {
		try {
			ProfileModel profileModel = profileService.addTitleToProfile(profileId, titleModel);
			return ResponseEntity.status(HttpStatus.OK).body(profileModel);
		} catch (CustomNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_SERVER);
		}
	}
}
