package com.api.unlatestcareer.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewWithUserReviewerModel {
    private int id;
    private String feedback;
    private Integer userRequesterId;
    private UserModelReview userReviewer;
    private Integer profileId;
}
