package com.api.unlatestcareer.helpers;

import com.api.unlatestcareer.entities.Review;
import com.api.unlatestcareer.models.ProfileModel;
import com.api.unlatestcareer.models.ReviewGetModel;
import com.api.unlatestcareer.models.UserModelReview;
import org.modelmapper.ModelMapper;

public class Converters {

    public ModelMapper mapper = new ModelMapper();

    public ReviewGetModel ReviewToReviewGetModel(Review review){
        ReviewGetModel reviewGetModel = new ReviewGetModel();

        reviewGetModel.setId(review.getId());
        reviewGetModel.setFeedback(review.getFeedback());
        reviewGetModel.setReviewer(mapper.map(review.getReviewer(), UserModelReview.class));
        reviewGetModel.setRequester(mapper.map(review.getRequester(), UserModelReview.class));
        reviewGetModel.setProfile(mapper.map(review.getProfile(), ProfileModel.class));

        return reviewGetModel;
    }



}
