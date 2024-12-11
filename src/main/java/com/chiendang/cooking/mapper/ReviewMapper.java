package com.chiendang.cooking.mapper;

import com.chiendang.cooking.api.auth.dto.response.UserResponse;
import com.chiendang.cooking.api.review.dto.ReviewResponse;
import com.chiendang.cooking.api.review.entiy.Review;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewMapper {

    public ReviewResponse toReviewResponse(Review review){
        return ReviewResponse.builder()
                .comment(review.getComment())
                .rating(review.getRating())
                .user((new UserResponse(review.getUser().getEmail(),review.getUser().getFirstName(),review.getUser().getLastName())))
                .build();
    }

     public List<ReviewResponse> toListReviewResponse(List<Review> reviews){
        return reviews.stream().map(this::toReviewResponse).toList();
    }
}
