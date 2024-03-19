package com.api.unlatestcareer.models;

import com.api.unlatestcareer.entities.Profile;
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
    private int userRequestReviewId;
    private int userReviewerId;
    private int profileId;

}
