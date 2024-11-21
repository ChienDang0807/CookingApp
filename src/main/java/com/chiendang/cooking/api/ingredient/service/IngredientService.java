package com.chiendang.cooking.api.ingredient.service;

import com.chiendang.cooking.api.ingredient.dto.IngredientResponse;
import com.chiendang.cooking.api.ingredient.entity.Ingredient;
import com.chiendang.cooking.api.ingredient.mapper.IngredientMapper;
import com.chiendang.cooking.api.ingredient.repository.IngredientRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IngredientService {
    IngredientRepository ingredientRepository;
    IngredientMapper ingredientMapper;





    public List<IngredientResponse> findAllIngredientByRecipeId(Integer id){
        List<Ingredient> ingredients  = ingredientRepository.findAllByRecipeId(id);
        return ingredients.stream().map(ingredientMapper::toInIngredientResponse).toList();
    }


}
