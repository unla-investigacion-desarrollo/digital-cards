package com.api.unlatestcareer.repositories;

import com.api.unlatestcareer.entities.Review;
import com.api.unlatestcareer.models.ReviewModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("reviewRepository")
public interface IReviewRepository extends JpaRepository<Review, Integer> {

    @Override
    @EntityGraph(attributePaths = {"requester","reviewer","profile"})
    List<Review> findAll();


}
