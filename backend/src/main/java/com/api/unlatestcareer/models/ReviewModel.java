package com.api.unlatestcareer.models;

import com.api.unlatestcareer.entities.Profile;
import com.api.unlatestcareer.entities.Review;
import com.api.unlatestcareer.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewModel {
    private int id;
    private String feedback;
    private Integer userRequesterId;
    private Integer userReviewerId;
    private Integer profileId;

    public ReviewModel(Integer userRequesterId, Integer profileId) {
        this.userRequesterId = userRequesterId;
        this.profileId = profileId;
    }
}
