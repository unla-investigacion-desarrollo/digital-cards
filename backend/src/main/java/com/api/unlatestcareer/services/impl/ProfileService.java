package com.api.unlatestcareer.services.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.api.unlatestcareer.helpers.Converters;
import com.api.unlatestcareer.helpers.ProfileStatus;
import com.api.unlatestcareer.models.*;
import com.api.unlatestcareer.services.IReviewService;
import com.api.unlatestcareer.services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.api.unlatestcareer.entities.Career;
import com.api.unlatestcareer.entities.Profile;
import com.api.unlatestcareer.exception.CustomNotFoundException;
import com.api.unlatestcareer.helpers.ViewRouteHelper;
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

    @Autowired
    private IUserService userService;

    @Autowired
    private IReviewService reviewService;

    @Override
    public ProfileModel findById(int id) {
        try {
            Optional<Profile> optionalProfile = profileRepository.findById(id);
            if (optionalProfile.isPresent()) {
                Profile profile = optionalProfile.get();
                return mapper.map(profile, ProfileModel.class);
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
            Optional<Profile> optionalProfile = profileRepository.findById(id);

            optionalProfile.ifPresentOrElse(profile -> {
                profile.setEnabled(false);
                profileRepository.save(profile);
            }, () -> {
                throw new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND);
            });
            return optionalProfile.isPresent();

        } catch (NoSuchElementException e) {
            throw new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND);
        } catch (DataAccessException e) {
            throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
        }
    }

    public ProfileModel save(ProfileModel profileModel) {
        try {
            Optional<Profile> optionalProfile = profileRepository.findById(profileModel.getId());
            Profile profileToSave;

            if (optionalProfile.isPresent()) {
                profileToSave = optionalProfile.get();
                profileToSave.setPhoto(profileModel.getPhoto());
                profileToSave.setCurrent(profileModel.isCurrent());
                profileToSave.setTitle(profileModel.getTitle());
                profileToSave.setStatus(profileModel.getStatus());
                profileToSave.setCourses(profileModel.getCourses());
                profileToSave.setInstitutions(profileModel.getInstitutions());
                profileToSave.setName(profileModel.getName());
                profileToSave.setProfileName(profileModel.getProfileName());
                profileToSave.setUrlLinkedin(profileModel.getUrlLinkedin());
                profileToSave.setMail(profileModel.getMail());
                profileToSave.setPhone(profileModel.getPhone());
                profileToSave.setMoreInfo(profileModel.getMoreInfo());
                profileToSave.setProjects(profileModel.getProjects());
                profileToSave.setEnabled(profileModel.isEnabled());
            } else {
                profileToSave = new Profile(profileModel.getPhoto(), profileModel.isCurrent(), profileModel.getTitle(),
                        profileModel.getStatus(), profileModel.getCourses(), profileModel.getInstitutions(),
                        profileModel.getName(), profileModel.getProfileName(), profileModel.getUrlLinkedin(),
                        profileModel.getMail(), profileModel.getPhone(), profileModel.getMoreInfo(),
                        profileModel.getProjects());
                profileToSave.setEnabled(true);
            }

            profileRepository.save(profileToSave);
            return mapper.map(profileToSave, ProfileModel.class);
        } catch (Exception e) {
            throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
        }
    }

    @Override
    public ProfileModel update(ProfileModel profile, int profileId) {
        //TODO: CUANDO SE UPDATE SE CAMBIA EL STATUS A PENDING?
        Profile profileExisting = profileRepository.findById(profileId)
                .orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
        profileExisting.setName(profile.getName());
        profileExisting.setTitle(profile.getTitle());
        profileExisting.setUrlLinkedin(profile.getUrlLinkedin());
        profileExisting.setPhone(profile.getPhone());
      //  profileExisting.setCareers(profile.get()); mirar carrer
        profileExisting.setMail(profile.getMail());
        profileExisting.setCourses(profile.getCourses());
        profileExisting.setPhoto(profile.getPhoto());
        profileExisting.setMoreInfo(profile.getMoreInfo());
        profileExisting.setProjects(profile.getProjects());
        profileExisting.setInstitutions(profile.getInstitutions());
        profileExisting.setStatus(ProfileStatus.PENDING);
        profileRepository.save(profileExisting);
        return mapper.map(profileExisting, ProfileModel.class);

    }

    @Override
    public void addCareerToProfile(int profileId, int careerId) {
        Profile profileExisting = profileRepository.findById(profileId).orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
        Career careerExisting = careerRepository.findById(careerId).orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
        if (profileExisting != null && careerExisting != null) {
            if (!profileExisting.getCareers().contains(careerExisting)) {
                profileExisting.getCareers().add(careerExisting);
            }
            profileRepository.save(profileExisting);
        }
        mapper.map(profileExisting, ProfileModel.class);
    }

    @Override
    public ProfileModel removeCareerFromProfile(int profileId, int careerId) {
        Profile profileExisting = profileRepository.findById(profileId).orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
        Career careerExisting = careerRepository.findById(careerId).orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
        if (profileExisting != null && careerExisting != null) {
            if (profileExisting.getCareers().contains(careerExisting)) {
                profileExisting.getCareers().remove(careerExisting);
            }
            profileRepository.save(profileExisting);
        }
        return mapper.map(profileExisting, ProfileModel.class);
    }

    public void disableAllProfiles() {
        List<ProfileModel> profiles = this.getAll();

        for (ProfileModel profile : profiles) {
            profile.setCurrent(false);
            this.save(profile);
        }
    }

    public List<ProfileModelWithReviews> profilesWithReviewList() {
        List<UserModel> userModelList = userService.findByEnabledTrue();
        List<ReviewWithUserReviewerModel> reviewModelsList = reviewService.getAllReviewModel();
        List<ProfileModelWithReviews> profileWithReviewsList = new ArrayList<>();
        Converters converters = new Converters();

        for (UserModel model : userModelList) {
            List<Profile> profiles = model.getProfiles().stream()
                    .filter(Profile::isEnabled).collect(Collectors.toList());

            for (Profile profile : profiles) {
                ProfileModelWithReviews profileWithReviews = new ProfileModelWithReviews();
                profileWithReviews.setUserModelReview(converters.userModelToUserModelReview(model));
                profileWithReviews.setProfileModel(converters.mapProfileToProfileModel(profile));

                List<ReviewWithUserReviewerModel> reviewModels = new ArrayList<>();

                for (ReviewWithUserReviewerModel reviewModel : reviewModelsList) {
                    if (reviewModel.getProfileId() == profile.getId()) {
                        reviewModels.add(reviewModel);
                    }
                }
                profileWithReviews.setReviewList(reviewModels);
                profileWithReviewsList.add(profileWithReviews);
            }
        }
        return profileWithReviewsList;
    }


    public boolean enableProfile(int id) {
        try {
            Optional<Profile> optionalProfile = profileRepository.findById(id);

            if (optionalProfile.isPresent()) {
                Profile profile = optionalProfile.get();
                profile.setEnabled(true);
                ProfileModel profileModel = mapper.map(profile, ProfileModel.class);
                profileRepository.save(profile);
                return true;
            } else {
                throw new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND);
            }
        } catch (Exception e) {
            throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
        }
    }
        public List<ProfileModel> findByEnabledTrue(){
            List<Profile> profiles = profileRepository.findByEnableTrue();
            return profiles.stream().map(profile->mapper.map(profile,ProfileModel.class)).collect(Collectors.toList());
        }
    }

