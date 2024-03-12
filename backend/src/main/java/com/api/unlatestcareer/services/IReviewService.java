package com.api.unlatestcareer.services;

import com.api.unlatestcareer.entities.Review;
import com.api.unlatestcareer.models.ReviewModel;

import java.util.List;

public interface IReviewService {
    ReviewModel findById(int id);

    List<ReviewModel> getAll();

    ReviewModel save(Review review);

}
