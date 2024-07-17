package com.api.unlatestcareer.models;

import com.api.unlatestcareer.entities.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewGetModel {
    private int id;
    private boolean enabled;
    private String feedback;
    private UserModelReview requester;
    private UserModelReview reviewer;
    private ProfileModel profile;
}
