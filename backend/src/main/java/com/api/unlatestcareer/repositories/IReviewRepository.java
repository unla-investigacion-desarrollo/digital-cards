package com.api.unlatestcareer.repositories;

import com.api.unlatestcareer.entities.Review;
import com.api.unlatestcareer.models.ReviewModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("reviewRepository")
public interface IReviewRepository extends JpaRepository<Review, Integer> {

    @Override
    @EntityGraph(attributePaths = {"requester","reviewer","profile"})
    List<Review> findAll();

    @Query(value = "SELECT r.* FROM career_test.review r " +
            "INNER JOIN ( " +
            "    SELECT profile_id, MAX(id) AS max_id " +
            "    FROM career_test.review " +
            "    GROUP BY profile_id " +
            ") latest_reviews " +
            "ON r.id = latest_reviews.max_id", nativeQuery = true)
    List<Review> findLatestReviewsByProfile();


}
