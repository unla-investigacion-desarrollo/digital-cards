package com.api.unlatestcareer.services.impl;

import com.api.unlatestcareer.entities.Profile;
import com.api.unlatestcareer.entities.Review;
import com.api.unlatestcareer.entities.User;
import com.api.unlatestcareer.exception.CustomNotFoundException;
import com.api.unlatestcareer.helpers.Converters;
import com.api.unlatestcareer.helpers.ViewRouteHelper;
import com.api.unlatestcareer.models.ProfileModel;
import com.api.unlatestcareer.models.ReviewGetModel;
import com.api.unlatestcareer.models.ReviewModel;
import com.api.unlatestcareer.models.ReviewWithUserReviewerModel;
import com.api.unlatestcareer.repositories.IProfileRepository;
import com.api.unlatestcareer.repositories.IReviewRepository;
import com.api.unlatestcareer.repositories.IUserRepository;
import com.api.unlatestcareer.services.IReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("reviewService")
public class ReviewService implements IReviewService {

    @Autowired
    private IReviewRepository reviewRepository;

    Converters converters = new Converters();

    @Autowired
    private IProfileRepository profileRepository;
    @Autowired
    private IUserRepository userRepository;
    private ModelMapper mapper = new ModelMapper();

    @Override
    public ReviewModel findById(int id) {
        try {
            Optional<Review> optionalReview = reviewRepository.findById(id);
            if (optionalReview.isPresent()) {
                Review review = optionalReview.get();
                return mapper.map(review, ReviewModel.class);
            } else {
                throw new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND);
            }
        } catch (Exception e) {
            throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
        }
    }

    @Override
    public List<ReviewGetModel> getAllReviewGetModels() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewGetModel> reviewGetModelList = new ArrayList<>();

        for(Review review : reviews){
        ReviewGetModel reviewGetModel = converters.ReviewToReviewGetModel(review);
        reviewGetModelList.add(reviewGetModel);
        }
        return reviewGetModelList;
    }

    //----- Check mapper
    @Override
    public List<ReviewWithUserReviewerModel> getAllReviewModel(){
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewWithUserReviewerModel> reviewModels = new ArrayList<>();

        for(Review review : reviews){
            ReviewWithUserReviewerModel reviewModel = mapper.map(review, ReviewWithUserReviewerModel.class);
            reviewModels.add(reviewModel);
        }
        return reviewModels;
    }

    @Override
    public ReviewModel save(ReviewModel review) {
        try {
            if(review.getUserReviewerId() != null){
                User requester = userRepository.findById(review.getUserRequesterId())
                        .orElseThrow(() -> new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND));

                User reviewer = userRepository.findById(review.getUserReviewerId())
                        .orElseThrow(() -> new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND));

                Profile profile = profileRepository.findById(review.getProfileId())
                        .orElseThrow(() -> new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND));

                Review reviewExisting = reviewRepository.findById(review.getId()).orElse(null);
                reviewExisting = new Review(review, requester, reviewer, profile);


                reviewRepository.save(reviewExisting);
                return mapper.map(reviewExisting, ReviewModel.class);
            }else {
                User requester = userRepository.findById(review.getUserRequesterId())
                        .orElseThrow(() -> new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND));

                Profile profile = profileRepository.findById(review.getProfileId())
                        .orElseThrow(() -> new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND));

                Review reviewExisting = reviewRepository.findById(review.getId()).orElse(null);
                reviewExisting = new Review(review, requester, null, profile);


                reviewRepository.save(reviewExisting);
                return mapper.map(reviewExisting, ReviewModel.class);
            }
        } catch (Exception e) {
            throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
        }
    }

    public ReviewModel addFeedback(ReviewModel review, int reviewId){
        Review reviewExisting = reviewRepository.findById(reviewId)
                .orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
        reviewExisting.setFeedback(review.getFeedback());
        User reviewer = userRepository.findById(review.getUserReviewerId())
                .orElseThrow(() -> new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND));
        reviewExisting.setReviewer(reviewer);
        reviewRepository.save(reviewExisting);

        return mapper.map(reviewExisting, ReviewModel.class);
    }


    public ReviewModel addUserRequestReviewToReview(int reviewId, int userId) {
        Review reviewExisting = reviewRepository.findById(reviewId)
                .orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
        User userExisting = userRepository.findById(userId)
                .orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
        if (reviewExisting != null && userExisting != null) {
            reviewExisting.setRequester(userExisting);
            reviewRepository.save(reviewExisting);

        }
        return mapper.map(reviewExisting, ReviewModel.class);
    }

    public ReviewModel addUserReviewerToReview(int reviewId, int userId) {

        Review reviewExisting = reviewRepository.findById(reviewId).orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
        User userExisting = userRepository.findById(userId).orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
        if (reviewExisting != null && userExisting != null) {
            reviewExisting.setReviewer(userExisting);
            reviewRepository.save(reviewExisting);


        }
        return mapper.map(reviewExisting, ReviewModel.class);
    }

    public ReviewModel addProfileToReview(int reviewId, int profileId) {

        Review reviewExisting = reviewRepository.findById(reviewId)
                .orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
        Profile profileExisting = profileRepository.findById(profileId)
                .orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
        if (reviewExisting != null && profileExisting != null) {
            reviewExisting.setProfile(profileExisting);
            reviewRepository.save(reviewExisting);

        }
        return mapper.map(reviewExisting, ReviewModel.class);
    }
}
