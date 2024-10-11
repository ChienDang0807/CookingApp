package com.chiendang.cooking.api.recipe.dto.request;

import com.chiendang.cooking.api.auth.entity.User;
import com.chiendang.cooking.api.category.entity.Category;
import com.chiendang.cooking.api.ingredient.entity.Ingredient;
import com.chiendang.cooking.api.instruction.entity.Instruction;
import com.chiendang.cooking.api.review.entiy.Review;
import jakarta.validation.constraints.NotBlank;
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
public class RecipeRequest {

    Integer id;

    @NotBlank(message = "NOT_BLANK")
    String recipeName;

    String prepTime;

    String cookTime;

    Set<Ingredient> ingredients;

    List<Instruction> instructions;

    Review review;

    Category category;

    User user;

    @NotBlank(message = "NOT_BLANK")
    String image;

    @NotBlank(message = "NOT_BLANK")
    String imageUrl;

}
