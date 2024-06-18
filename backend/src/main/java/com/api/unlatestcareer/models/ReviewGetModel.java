package com.api.unlatestcareer.models;

import com.api.unlatestcareer.entities.Profile;
import lombok.Data;

@Data
public class ReviewGetModel {
    private int id;
    private boolean enabled;
    private String feedback;
    private UserModelReview requester;
    private UserModelReview reviewer;
    private ProfileModel profile;

}
