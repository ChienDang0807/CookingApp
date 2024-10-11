package com.chiendang.cooking.api.recipe.mapper;


import com.chiendang.cooking.api.recipe.dto.request.RecipeRequest;
import com.chiendang.cooking.api.recipe.dto.response.RecipeResponse;
import com.chiendang.cooking.api.recipe.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    Recipe toRecipe(RecipeRequest request);
    @Mapping(target = "imageUrl" , ignore = true)
    RecipeResponse toRecipeResponse(Recipe recipe);
    List<RecipeResponse> toRecipeResponseList(List<Recipe> recipes);
}
