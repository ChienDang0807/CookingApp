package com.chiendang.cooking.api.review.dto;

import com.chiendang.cooking.api.auth.entity.User;
import com.chiendang.cooking.entity.Recipe;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewResponse {

    private User user;

    private Recipe recipe;

    String comment;

    Integer rating;
}
