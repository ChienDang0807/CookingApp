package com.chiendang.cooking.api.recipe.mapper;


import com.chiendang.cooking.api.auth.dto.response.UserResponse;
import com.chiendang.cooking.api.auth.entity.User;
import com.chiendang.cooking.api.ingredient.dto.IngredientResponse;
import com.chiendang.cooking.api.ingredient.entity.Ingredient;
import com.chiendang.cooking.api.instruction.dto.InstructionResponse;
import com.chiendang.cooking.api.instruction.entity.Instruction;
import com.chiendang.cooking.api.recipe.dto.request.RecipeRequest;
import com.chiendang.cooking.api.recipe.dto.response.RecipeResponse;
import com.chiendang.cooking.api.recipe.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userCreated", ignore = true)
    @Mapping(target = "reviewers", ignore = true)
    @Mapping(target = "userFavoriteRecipe",ignore = true)

    Recipe toRecipe(RecipeRequest request);

    @Mapping(target = "imageUrl", ignore = true)
    @Mapping(target = "user", expression = "java(mapUserResponse(recipe.getUserCreated()))" )
    @Mapping(target = "ingredients", expression = "java(mapIngredients(recipe.getIngredients()))")// Sử dụng phương thức ánh xạ tùy chỉnh cho ingredients
    @Mapping(target = "instructions", expression = "java(mapInstructions(recipe.getInstructions()))")
    RecipeResponse toRecipeResponse(Recipe recipe);


    default Set<IngredientResponse> mapIngredients(Set<Ingredient> ingredients) {

        return ingredients.stream()
                .map(ingredient -> new IngredientResponse(ingredient.getId(), ingredient.getName(), ingredient.getAmount())) // Tạo đối tượng IngredientResponse từ Ingredient
                .collect(Collectors.toSet());
    }

    default List<InstructionResponse> mapInstructions(List<Instruction> instructions) {

        return instructions.stream()
                .map(instruction -> new InstructionResponse(instruction.getId(), instruction.getStepNumber(), instruction.getDescription())) // Tạo đối tượng IngredientResponse từ Ingredient
                .collect(Collectors.toList());
    }
    default UserResponse mapUserResponse(User user){
        return  new UserResponse(user.getFirstName(), user.getLastName());
    }
}
