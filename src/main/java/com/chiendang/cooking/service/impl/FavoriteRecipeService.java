package com.chiendang.cooking.service.impl;

import com.chiendang.cooking.api.auth.entity.User;
import com.chiendang.cooking.api.auth.repository.UserRespository;
import com.chiendang.cooking.repository.FavoriteRecipeRepository;
import com.chiendang.cooking.dto.response.RecipeResponse;
import com.chiendang.cooking.entity.Recipe;
import com.chiendang.cooking.mapper.RecipeMapper;
import com.chiendang.cooking.exception.AppExceptions;
import com.chiendang.cooking.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FavoriteRecipeService {
    FavoriteRecipeRepository favoriteRecipeRepository;
    UserRespository userRespository;
    RecipeMapper recipeMapper;

    public List<RecipeResponse> getFavoriteRecipes() {
        var context = SecurityContextHolder.getContext();
        String name= context.getAuthentication().getName();

         User user = userRespository.findByEmail(name)
                .orElseThrow(() -> new AppExceptions(ErrorCode.USER_NOT_EXISTED));

         List<Recipe> recipes = favoriteRecipeRepository.findFavoriteRecipesByUserId(user.getId());

         if(recipes == null || recipes.isEmpty()){
             throw new AppExceptions(ErrorCode.LIST_EMPTY);
         }

        return recipes.stream().map(recipeMapper::toRecipeResponse).toList();
    }

}
