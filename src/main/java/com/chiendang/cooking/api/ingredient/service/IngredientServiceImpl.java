package com.chiendang.cooking.api.ingredient.service;

import com.chiendang.cooking.api.ingredient.dto.IngredientRequest;
import com.chiendang.cooking.api.ingredient.dto.IngredientResponse;
import com.chiendang.cooking.api.ingredient.entity.Ingredient;
import com.chiendang.cooking.api.ingredient.mapper.IngredientMapper;
import com.chiendang.cooking.api.ingredient.repository.IngredientRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IngredientServiceImpl implements  IngredientService{
    IngredientRepository ingredientRepository;
    IngredientMapper ingredientMapper;


    @Override
    public Set<IngredientResponse> addAllIngredient(Set<IngredientRequest> list){

        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("The list of ingredients is null or empty.");
        }

        Set<Ingredient> ingredientsToSave = ingredientMapper.toListIngredient(list);

        Set<Ingredient> savedIngredients = new HashSet<>(ingredientRepository.saveAll(ingredientsToSave));

        return ingredientMapper.toListIngredientResponse(savedIngredients);
    }

    @Override
    public Set<IngredientResponse> findAllIngredientByRecipeId(Integer id){
        return  ingredientMapper.toListIngredientResponse(new HashSet<>(ingredientRepository.findAllByRecipeId(id)));
    }


}
