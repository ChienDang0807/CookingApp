package com.chiendang.cooking.api.ingredient.dto;

import com.chiendang.cooking.api.recipe.dto.response.RecipeResponse;
import com.chiendang.cooking.api.recipe.entity.Recipe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientResponse {
    Integer id;
    String name;
    String amount;
    @JsonIgnore
    RecipeResponse recipe;
}
