package com.chiendang.cooking.api.review.dto;

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

    private Integer userId;

    private Integer recipeId;

    String comment;

    Integer rating;
}
