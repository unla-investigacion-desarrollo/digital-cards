package com.api.unlatestcareer.helpers;

import com.api.unlatestcareer.entities.Profile;
import com.api.unlatestcareer.entities.Review;
import com.api.unlatestcareer.models.*;
import org.modelmapper.ModelMapper;

public class Converters {

    public ModelMapper mapper = new ModelMapper();

    public ReviewGetModel ReviewToReviewGetModel(Review review){
        ReviewGetModel reviewGetModel = new ReviewGetModel();

        reviewGetModel.setId(review.getId());
        reviewGetModel.setFeedback(review.getFeedback());
        if(review.getReviewer() != null) {
            reviewGetModel.setReviewer(mapper.map(review.getReviewer(), UserModelReview.class));
        }
        reviewGetModel.setRequester(mapper.map(review.getRequester(), UserModelReview.class));
        reviewGetModel.setProfile(mapper.map(review.getProfile(), ProfileModel.class));

        return reviewGetModel;
    }

    public ProfileModel mapProfileToProfileModel (Profile profile){
        ProfileModel profileModel = new ProfileModel();
        profileModel.setId(profile.getId());
        profileModel.setPhoto(profile.getPhoto());
        profileModel.setCurrent(profile.isCurrent());
        profileModel.setTitle(profile.getTitle());
        profileModel.setStatus(profile.getStatus());
        profileModel.setCourses(profile.getCourses());
        profileModel.setName(profile.getName());
        profileModel.setProjects(profile.getProjects());
        profileModel.setUrlLinkedin(profile.getUrlLinkedin());
        profileModel.setMail(profile.getMail());
        profileModel.setPhone(profile.getPhone());
        profileModel.setMoreInfo(profile.getMoreInfo());
        profileModel.setInstitutions(profile.getInstitutions());

        return profileModel;
    }

    public UserModelReview userModelToUserModelReview (UserModel model){
        UserModelReview userModelReview = new UserModelReview();
        userModelReview.setId(model.getId());
        userModelReview.setRole(model.getRole());
        userModelReview.setEnabled(model.isEnabled());
        userModelReview.setUsername(model.getUsername());

        return userModelReview;
    }

}
