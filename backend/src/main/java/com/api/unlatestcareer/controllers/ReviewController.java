package com.api.unlatestcareer.controllers;


import com.api.unlatestcareer.entities.Review;
import com.api.unlatestcareer.helpers.ViewRouteHelper;
import com.api.unlatestcareer.models.ProfileModel;
import com.api.unlatestcareer.models.ReviewModel;
import com.api.unlatestcareer.services.impl.ProfileService;
import com.api.unlatestcareer.services.impl.ReviewService;
import com.api.unlatestcareer.services.impl.UserService;
import com.api.unlatestcareer.services.impl.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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


    @PostMapping("")
    public ResponseEntity<?> createReview(@RequestBody ReviewModel model) {
        try {
            Review review = new Review(model.getId(),model.getFeedback(),null,null,null,
                    null, null);
            ReviewModel savedProfile = reviewService.save(review);
            if (savedProfile != null) {

                return ResponseEntity.status(HttpStatus.OK).body("review agregado exitosamente "
                        + SecurityContextHolder.getContext().getAuthentication().getName());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_CREATE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ViewRouteHelper.ERROR_REQUEST);
        }
    }


    @GetMapping("")
    public ResponseEntity<?> getAllReviews() {
        try {
            if (UtilService.hasRole(ViewRouteHelper.ADMIN_ROLE)) {
                List<ReviewModel> reviews = reviewService.getAll();
                return ResponseEntity.ok(reviews);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ViewRouteHelper.ACCESS_DENIED);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_SERVER);
        }
    }

}
