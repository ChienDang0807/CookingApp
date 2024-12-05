package com.chiendang.cooking.api.ingredient.service;

import com.chiendang.cooking.api.ingredient.dto.IngredientRequest;
import com.chiendang.cooking.api.ingredient.dto.IngredientResponse;

import java.util.Set;

public interface IngredientService {
    Set<IngredientResponse> addAllIngredient(Set<IngredientRequest> list);
    Set<IngredientResponse> findAllIngredientByRecipeId(Integer id);
}
