package com.api.unlatestcareer.services;

import com.api.unlatestcareer.models.ReviewGetModel;
import com.api.unlatestcareer.models.ReviewModel;

import java.util.List;

public interface IReviewService {
    ReviewModel findById(int id);

    List<ReviewGetModel> getAllReviewGetModels();

    ReviewModel save(ReviewModel review);
}
