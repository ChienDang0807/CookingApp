package com.chiendang.cooking.api.recipe.dto.response;

import com.chiendang.cooking.api.auth.dto.response.UserResponse;
import com.chiendang.cooking.api.category.dto.CategoryResponse;
import com.chiendang.cooking.api.ingredient.dto.IngredientResponse;
import com.chiendang.cooking.api.instruction.dto.InstructionResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeResponse {

    Integer id;

    String recipeName;

    String prepTime;

    String cookTime;

    Set<IngredientResponse> ingredients;

    List<InstructionResponse> instructions;

    CategoryResponse category;

    UserResponse user;

    String image;

    String imageUrl;
}
