package com.chiendang.cooking.api.instruction.dto;

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
public class InstructionRequest {


    Integer stepNumber;

    String description;

    RecipeRequest recipeId;

    String addInfor1;

    String addInfor2;


}
