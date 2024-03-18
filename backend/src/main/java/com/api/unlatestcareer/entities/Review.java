package com.api.unlatestcareer.entities;


import com.api.unlatestcareer.models.ReviewModel;
import com.api.unlatestcareer.models.UserModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="review")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String feedback;
    private LocalDate createdAt;
    private LocalDate updateAt;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_request_review_id")
    private User userRequestReview;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_reviewer_id")
    private User userReviewer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profiles;



    public Review(String feedback) {
        this.feedback = feedback;
        this.userReviewer = new User();
        this.userRequestReview = new User();
        this.profiles = new Profile();
        this.createdAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public Review (ReviewModel model){
        this.id = model.getId();
        this.feedback = model.getFeedback();
    }
}
