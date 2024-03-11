package com.api.unlatestcareer.services.impl;

import com.api.unlatestcareer.entities.Review;
import com.api.unlatestcareer.exception.CustomNotFoundException;
import com.api.unlatestcareer.helpers.ViewRouteHelper;
import com.api.unlatestcareer.models.CareerModel;
import com.api.unlatestcareer.models.ReviewModel;
import com.api.unlatestcareer.repositories.IReviewRepository;
import com.api.unlatestcareer.services.IReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service("reviewService")
public class ReviewService implements IReviewService {

    @Autowired
    private IReviewRepository reviewRepository;

    private ModelMapper mapper = new ModelMapper();

    @Override
    public ReviewModel findById(int id) {
        return null;
    }

    @Override
    public List<ReviewModel> getAll() {
        return reviewRepository.findAll().stream().map(review -> mapper.map(review, ReviewModel.class)).collect(Collectors.toList());

    }

    @Override
    public ReviewModel save(Review review) {
        try {
            Review reviewExisting = reviewRepository.findById(review.getId()).orElse(null);

            if (reviewExisting == null) {
                reviewExisting = new Review(review.getId(),review.getFeedback(),LocalDate.now(),LocalDate.now(),review.getUserRequestReview(),review.getUserReviewer(),review.getProfiles());
            } else {
                reviewExisting = new Review(review);
            }
            reviewRepository.save(reviewExisting);
            return mapper.map(reviewExisting, ReviewModel.class);
        } catch (Exception e) {
            throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
        }

    }


}
