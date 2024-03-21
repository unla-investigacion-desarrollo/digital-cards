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
@Table(name="review")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String feedback;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_requester_id")
    private User requester;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_reviewer_id")
    private User reviewer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    private LocalDate createdAt;
    private LocalDate updateAt;

    public Review (ReviewModel model,User requester,User reviewer,Profile profile){
        this.id = model.getId();
        this.feedback = model.getFeedback();
        this.setReviewer(reviewer);
        this.setRequester(requester);
        this.setProfile(profile);
        this.createdAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

}
