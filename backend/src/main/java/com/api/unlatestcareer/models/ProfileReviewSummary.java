package com.api.unlatestcareer.models;

import com.api.unlatestcareer.entities.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileReviewSummary {
    private ProfileModel profileModel;
    private ReviewSummary reviewSummary;
}
