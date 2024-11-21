package com.chiendang.cooking.api.ingredient.dto;

import com.chiendang.cooking.api.recipe.dto.request.RecipeRequest;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientRequest {

    String name;

    String amount;

    RecipeRequest recipe;

    String addInfor1;
    String addInfor2;
}
