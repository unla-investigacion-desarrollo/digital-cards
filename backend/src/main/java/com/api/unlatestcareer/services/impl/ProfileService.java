package com.api.unlatestcareer.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.unlatestcareer.entities.Career;
import com.api.unlatestcareer.entities.Profile;
import com.api.unlatestcareer.exception.CustomNotFoundException;
import com.api.unlatestcareer.helpers.ViewRouteHelper;
import com.api.unlatestcareer.models.ProfileModel;
import com.api.unlatestcareer.repositories.ICareerRepository;
import com.api.unlatestcareer.repositories.IProfileRepository;
import com.api.unlatestcareer.services.IProfileService;

@Service("profileService")
public class ProfileService implements IProfileService {

	private ModelMapper mapper = new ModelMapper();

	@Autowired
	private IProfileRepository profileRepository;
	@Autowired
	private ICareerRepository careerRepository;

	@Override
	public ProfileModel findById(int id) {
		try {
			Optional<Profile> optionalProfile = profileRepository.findById(id);
			if (optionalProfile.isPresent()) {
				Profile profile = optionalProfile.get();
				ProfileModel profileModel = mapper.map(profile, ProfileModel.class);
				return profileModel;
			} else {
				throw new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND);
			}
		} catch (Exception e) {
			throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
		}
	}

	@Override
	public ProfileModel findByName(String name) {
		ProfileModel profileModel = profileRepository.findByName(name);
		if (profileModel != null) {
			return profileModel;
		}
		return null;
	}

	@Override
	public List<ProfileModel> getAll() {
		List<Profile> profiles = profileRepository.findAll();
		return profiles.stream().map(profile -> mapper.map(profile, ProfileModel.class)).collect(Collectors.toList());
	}

	@Override
	public boolean deleteById(int id) {
		try {
			Profile profileExisting = profileRepository.findById(id).orElse(null);
			boolean deleted = false;
			if (profileExisting != null) {
				profileRepository.deleteById(profileExisting.getId());
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
	public ProfileModel save(ProfileModel profile) {
		try {
			Profile profileExisting = profileRepository.findById(profile.getId()).orElse(null);
			if (profileExisting == null) {
				profileExisting = new Profile(profile.getPhoto(), profile.isCurrent(), profile.getTitle(),
						profile.getStatus(), profile.getCourses(), profile.getInstitutions(),profile.getName(),
						profile.getUrlLinkedin(), profile.getMail(), profile.getPhone(), profile.getMoreInfo(), profile.getProjects());
			} else {
				profileExisting = new Profile(profile);
			}
			profileRepository.save(profileExisting);
			return mapper.map(profileExisting, ProfileModel.class);
		} catch (Exception e) {
			throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
		}
	}

	@Override
	public ProfileModel update(ProfileModel profile, int profileId) {
		Profile profileExisting = profileRepository.findById(profileId)
				.orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
		profileExisting.setName(profile.getName());
		return mapper.map(profileExisting, ProfileModel.class);

	}

	@Override
	public ProfileModel addCareerToProfile(int profileId, int careerId) {
		Profile profileExisting = profileRepository.findById(profileId)
				.orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
		Career careerExisting = careerRepository.findById(careerId)
				.orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
		if (profileExisting != null && careerExisting != null) {
			if (!profileExisting.getCareers().contains(careerExisting)) {
				profileExisting.getCareers().add(careerExisting);
			}
			profileRepository.save(profileExisting);
		}
		return mapper.map(profileExisting, ProfileModel.class);
	}

	@Override
	public ProfileModel removeCareerFromProfile(int profileId, int careerId) {
		Profile profileExisting = profileRepository.findById(profileId)
				.orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
		Career careerExisting = careerRepository.findById(careerId)
				.orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
		if (profileExisting != null && careerExisting != null) {
			if (profileExisting.getCareers().contains(careerExisting)) {
				profileExisting.getCareers().remove(careerExisting);
			}
			profileRepository.save(profileExisting);
		}
		return mapper.map(profileExisting, ProfileModel.class);
	}
}
