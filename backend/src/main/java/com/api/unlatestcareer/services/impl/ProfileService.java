package com.api.unlatestcareer.services.impl;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.api.unlatestcareer.entities.User;
import com.api.unlatestcareer.helpers.Converters;
import com.api.unlatestcareer.models.*;
import com.api.unlatestcareer.repositories.IUserRepository;
import com.api.unlatestcareer.services.IReviewService;
import com.api.unlatestcareer.services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
                        profile.getStatus(), profile.getCourses(), profile.getInstitutions(), profile.getName(),
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

    public void disableAllProfiles() {
        List<ProfileModel> profiles = this.getAll();

        for (ProfileModel profile : profiles) {
            profile.setCurrent(false);
            this.save(profile);
        }
    }

    public List<ProfileModelWithReviews> profilesWithReviewList(){
        List<UserModel> userModelList = userService.getAll();
        List<ReviewWithUserReviewerModel> reviewModelsList = reviewService.getAllReviewModel();
        List<ProfileModelWithReviews> profileWithReviewsList = new ArrayList<>();
        Converters converters = new Converters();

        for(UserModel model : userModelList ){
           Set<Profile> profiles = model.getProfiles();

           for(Profile profile : profiles){
               ProfileModelWithReviews profileWithReviews = new ProfileModelWithReviews();
            profileWithReviews.setUserModelReview(converters.userModelToUserModelReview(model));
            profileWithReviews.setProfileModel(converters.mapProfileToProfileModel(profile));

            List<ReviewWithUserReviewerModel> reviewModels = new ArrayList<>();

            for(ReviewWithUserReviewerModel reviewModel : reviewModelsList) {
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
}
