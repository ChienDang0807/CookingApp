package com.chiendang.cooking.api.recipe.service;

import com.chiendang.cooking.api.recipe.dto.response.PageResponse;
import com.chiendang.cooking.api.recipe.dto.response.RecipeResponse;

import java.util.List;

public interface RecipeService {
    List<RecipeResponse> getAllRecipe ();
    PageResponse<?> findRecipeByLimit (String name, long lastId , int limit);
}
