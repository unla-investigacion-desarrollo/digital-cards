package com.api.unlatestcareer.controllers;

import com.api.unlatestcareer.entities.Review;
import com.api.unlatestcareer.helpers.ViewRouteHelper;
import com.api.unlatestcareer.models.ReviewGetModel;
import com.api.unlatestcareer.models.ReviewModel;
import com.api.unlatestcareer.services.impl.ProfileService;
import com.api.unlatestcareer.services.impl.ReviewService;
import com.api.unlatestcareer.services.impl.UserService;
import com.api.unlatestcareer.services.impl.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    public ReviewController(ReviewService reviewService) {

        this.reviewService = reviewService;
    }

    @PostMapping("")
    public ResponseEntity<?> createReview(@RequestBody ReviewModel model) {
        try {
            ReviewModel savedReview = reviewService.save(model);

            if (savedReview != null) {
                reviewService.addUserRequestReviewToReview(savedReview.getId(),model.getUserRequesterId());
                if(model.getUserReviewerId() != null ){
                    reviewService.addUserReviewerToReview(savedReview.getId(), model.getUserReviewerId());
                }

                reviewService.addProfileToReview(savedReview.getId(),model.getProfileId());
                return ResponseEntity.status(HttpStatus.OK).body(savedReview);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_CREATE);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ViewRouteHelper.ERROR_REQUEST);
        }
    }

    @PatchMapping("/{id}/addFeedback")
    public ResponseEntity<?> addFeedback(@PathVariable int id,@RequestBody ReviewModel model) {
        try {
            ReviewModel updatedReview = reviewService.addFeedback(model,id);

            if (updatedReview != null) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedReview);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ViewRouteHelper.ERROR_NOTFOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ViewRouteHelper.ERROR_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAllReviews() {
        try {
            if (UtilService.hasRole(ViewRouteHelper.ADMIN_ROLE)) {
                List<ReviewGetModel> reviews = reviewService.getAllReviewGetModels();
                return ResponseEntity.ok(reviews);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ViewRouteHelper.ACCESS_DENIED);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_SERVER);
        }
    }
}
