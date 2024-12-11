package com.chiendang.cooking.api.review.repository;

import com.chiendang.cooking.api.review.entiy.Review;
import com.chiendang.cooking.api.review.entiy.ReviewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
    @Query("SELECT r FROM Review r WHERE r.recipe.id = :recipeId")
    List<Review> findAllByRecipeId(@Param("recipeId") Integer recipeId);
}
