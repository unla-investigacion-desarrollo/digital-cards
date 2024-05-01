package com.api.unlatestcareer.services;

import com.api.unlatestcareer.entities.Review;
import com.api.unlatestcareer.models.ReviewGetModel;
import com.api.unlatestcareer.models.ReviewModel;
import com.api.unlatestcareer.models.ReviewWithUserReviewerModel;

import java.util.List;

public interface IReviewService {
    ReviewModel findById(int id);

    List<ReviewGetModel> getAllReviewGetModels();

    List<ReviewWithUserReviewerModel> getAllReviewModel();

    ReviewModel save(ReviewModel review);
}
