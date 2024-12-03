package com.chiendang.cooking.api.favorite_recipe;

import com.chiendang.cooking.api.auth.dto.response.ResponseData;
import com.chiendang.cooking.api.auth.dto.response.ResponseError;
import com.chiendang.cooking.api.favorite_recipe.service.FavoriteRecipeService;
import com.chiendang.cooking.api.recipe.dto.response.RecipeResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/favorite-recipe")
@RequiredArgsConstructor
@Tag(name = "favorite-recipe")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FavoriteRecipeController {
    FavoriteRecipeService favoriteRecipeService;

    @GetMapping
    public ResponseData<List<RecipeResponse>> getFavoriteRecipe(){
        try {
            log.info("Get favorite recipe successfully");
            return new ResponseData<>(HttpStatus.OK.value(), "Get recipe succesfully",favoriteRecipeService.getFavoriteRecipes());
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
