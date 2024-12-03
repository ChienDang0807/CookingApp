package com.chiendang.cooking.api.ingredient.dto;

import com.chiendang.cooking.api.recipe.dto.response.RecipeResponse;
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

    public IngredientResponse(Integer id, String name, String amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }
}
