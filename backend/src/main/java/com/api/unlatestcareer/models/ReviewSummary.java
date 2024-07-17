package com.api.unlatestcareer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSummary {
    private String feedback;
    private UserModelReview reviewer;
}
