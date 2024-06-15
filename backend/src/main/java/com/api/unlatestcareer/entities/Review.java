package com.api.unlatestcareer.entities;

import com.api.unlatestcareer.models.ReviewModel;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
public class Review extends BaseEntityAudit {

    private String feedback;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_requester_id")
    private User requester;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_reviewer_id", nullable = true) // Hacer reviewer opcional
    private User reviewer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public Review(ReviewModel model, User requester, User reviewer, Profile profile) {
        this.setId(model.getId());
        this.feedback = model.getFeedback();
        this.setReviewer(reviewer);
        this.setRequester(requester);
        this.setProfile(profile);
    }
}
