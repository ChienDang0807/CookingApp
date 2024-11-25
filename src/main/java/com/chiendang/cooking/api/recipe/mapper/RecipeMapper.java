package com.chiendang.cooking.api.recipe.mapper;


import com.chiendang.cooking.api.recipe.dto.request.RecipeRequest;
import com.chiendang.cooking.api.recipe.dto.response.RecipeResponse;
import com.chiendang.cooking.api.recipe.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userCreated", ignore = true)
    @Mapping(target = "reviewers", ignore = true)
    @Mapping(target = "userFavoriteRecipe",ignore = true)
    Recipe toRecipe(RecipeRequest request);

    @Mapping(target = "imageUrl" , ignore = true)
    RecipeResponse toRecipeResponse(Recipe recipe);

}
