package com.chiendang.cooking.api.ingredient.mapper;

import com.chiendang.cooking.api.ingredient.dto.IngredientResponse;
import com.chiendang.cooking.api.ingredient.entity.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {

    public IngredientResponse toInIngredientResponse(Ingredient i) {
        if (i == null) {
            return null;
        } else {
            IngredientResponse.IngredientResponseBuilder ingredientResponse = IngredientResponse.builder();
            ingredientResponse.id(i.getId());
            ingredientResponse.name(i.getName());
            ingredientResponse.amount(i.getAmount());

            return ingredientResponse.build();
        }
    }

}
