package com.chiendang.cooking.api.ingredient.mapper;

import com.chiendang.cooking.api.ingredient.dto.IngredientRequest;
import com.chiendang.cooking.api.ingredient.dto.IngredientResponse;
import com.chiendang.cooking.api.ingredient.entity.Ingredient;
import com.chiendang.cooking.api.recipe.dto.request.RecipeRequest;
import com.chiendang.cooking.api.recipe.dto.response.RecipeResponse;
import com.chiendang.cooking.api.recipe.entity.Recipe;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    IngredientResponse toInIngredientResponse(Ingredient i);

}
