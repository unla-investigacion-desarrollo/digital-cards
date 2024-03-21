package com.api.unlatestcareer.services.impl;

import com.api.unlatestcareer.entities.Profile;
import com.api.unlatestcareer.entities.Review;
import com.api.unlatestcareer.entities.User;
import com.api.unlatestcareer.exception.CustomNotFoundException;
import com.api.unlatestcareer.helpers.ViewRouteHelper;
import com.api.unlatestcareer.models.ReviewModel;
import com.api.unlatestcareer.repositories.IProfileRepository;
import com.api.unlatestcareer.repositories.IReviewRepository;
import com.api.unlatestcareer.repositories.IUserRepository;
import com.api.unlatestcareer.services.IReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.View;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("reviewService")
public class ReviewService implements IReviewService {

    @Autowired
    private IReviewRepository reviewRepository;

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
    public List<ReviewModel> getAll() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewModel> reviewModels = new ArrayList<>();
        for (Review review : reviews) {
            ReviewModel reviewModel = mapper.map(review, ReviewModel.class);

            Profile profile = review.getProfile();
            if (profile != null) {
                reviewModel.setProfileId(profile.getId());
            }
            reviewModels.add(reviewModel);
        }

        return reviewModels;
    }

    @Override
    public ReviewModel save(ReviewModel review) {
        try {
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
        } catch (Exception e) {
            throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
        }
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
