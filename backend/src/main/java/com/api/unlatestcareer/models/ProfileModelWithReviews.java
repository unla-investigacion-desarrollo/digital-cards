package com.api.unlatestcareer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileModelWithReviews {
    private UserModelReview userModelReview;
    private ProfileModel profileModel;
    private List<ReviewWithUserReviewerModel> reviewList;
}