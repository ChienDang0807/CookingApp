package com.chiendang.cooking.api.review.service;

import com.chiendang.cooking.api.auth.dto.response.UserResponse;
import com.chiendang.cooking.api.auth.entity.User;
import com.chiendang.cooking.api.auth.service.UserService;
import com.chiendang.cooking.api.review.dto.ReviewRequest;
import com.chiendang.cooking.api.review.dto.ReviewResponse;
import com.chiendang.cooking.api.review.entiy.Review;
import com.chiendang.cooking.api.review.entiy.ReviewId;
import com.chiendang.cooking.entity.Recipe;
import com.chiendang.cooking.exception.AppExceptions;
import com.chiendang.cooking.exception.ErrorCode;
import com.chiendang.cooking.mapper.ReviewMapper;
import com.chiendang.cooking.service.impl.RecipeServiceImpl;
import com.chiendang.cooking.api.review.repository.ReviewRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
 public class ReviewService {
    ReviewRepository reviewRepository;
    RecipeServiceImpl recipeService;
    UserService userService;
    ReviewMapper reviewMapper;


    public ReviewResponse createNewComment(ReviewRequest request) {
        // Tạo UUID cho commentIndex
        String commentIndex = UUID.randomUUID().toString();

        // Tạo khóa chính phức hợp
        ReviewId reviewId = new ReviewId();
        reviewId.setUserId(request.getUserId());
        reviewId.setRecipeId(request.getRecipeId());
        reviewId.setCommentIndex(commentIndex);

        // Tìm user và recipe nếu cần (không bắt buộc nếu chỉ cần ID)
        Optional<User> user = userService.getUserById(request.getUserId());
        if(user.isEmpty()){
            throw new AppExceptions(ErrorCode.USER_NOT_EXISTED);
        }
        Recipe recipe = recipeService.getRecipeInfo(request.getRecipeId());

        // Tạo đối tượng Review
        Review review = new Review();
        review.setId(reviewId);
        review.setComment(request.getComment());
        review.setRating(request.getRating());
        review.setUser(user.get());
        review.setRecipe(recipe);

        return reviewMapper.toReviewResponse(reviewRepository.save(review));
    }



//    public ReviewResponse updateReview(ReviewId id, ReviewRequest request){
//        Review review =reviewRepository.findById(id)
//                .orElseThrow(() -> new AppExceptions(ErrorCode.RESOURCES_NOT_FOUND));
//
//
//        Review reviewUpdate = new Review(
//                review.getId(),
//                request.getComment(),
//                request.getRating()
//        );

//        reviewRepository.save(reviewUpdate);
//
//        return ReviewResponse.builder()
//                .userId(id.getUserId())
//                .recipeId(id.getRecipeId())
//                .rating(review.getRating())
//                .comment(review.getComment())
//                .build();
//    }
//
    public void deleteReview (ReviewId id){
        Review  review =reviewRepository.findById(id)
                .orElseThrow(() -> new AppExceptions(ErrorCode.RESOURCES_NOT_FOUND));
        reviewRepository.delete(review);
    }

    public List<ReviewResponse> getAllReviewByRecipeId(Integer recipeId){
        return reviewMapper.toListReviewResponse(reviewRepository.findAllByRecipeId(recipeId));
    }


}
