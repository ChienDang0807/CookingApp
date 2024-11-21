package com.chiendang.cooking.api.review.dto;

import com.chiendang.cooking.api.auth.dto.request.UserCreationRequest;
import com.chiendang.cooking.api.auth.entity.User;
import com.chiendang.cooking.api.recipe.dto.request.RecipeRequest;
import com.chiendang.cooking.api.recipe.entity.Recipe;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewRequest {

    private User user;

    private Recipe recipe;

    String comment;

    Integer rating;

    String addInfor1;

    String addInfor2;

    String addInfor3;
}
