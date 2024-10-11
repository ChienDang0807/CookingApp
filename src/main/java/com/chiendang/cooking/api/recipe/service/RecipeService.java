package com.chiendang.cooking.api.recipe.service;

import com.chiendang.cooking.api.recipe.dto.request.RecipeRequest;
import com.chiendang.cooking.api.recipe.dto.response.RecipeResponse;
import com.chiendang.cooking.api.recipe.entity.Recipe;
import com.chiendang.cooking.api.recipe.mapper.RecipeMapper;
import com.chiendang.cooking.api.recipe.respository.RecipeRepository;
import com.chiendang.cooking.api.image.service.ImageService;
import com.chiendang.cooking.exception.AppExceptions;
import com.chiendang.cooking.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeService {

    RecipeRepository recipeRepository;
    ImageService fileService;
    RecipeMapper recipeMapper;

    @NonFinal
    @Value("${project.foodimage}")
    String path;

    @NonFinal
    @Value("${base.url}")
    String baseUrl;

    public RecipeResponse addRecipe(RecipeRequest request, MultipartFile file) throws IOException {
        if (Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))){
            throw  new AppExceptions(ErrorCode.FILE_IS_EXISTED);
        }
        String uploadedFileName = fileService.uploadFile(path,file);

        //2. set the value of field 'poster' as filename
        request.setImage(uploadedFileName);

        Recipe recipe = recipeMapper.toRecipe(request);

        Recipe savedRecipe =  recipeRepository.save(recipe);

        String imageUrl = baseUrl + "/file/" +uploadedFileName;

        RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(savedRecipe);

        recipeResponse.setImageUrl(imageUrl);

        return  recipeResponse;
    }

    public RecipeResponse getRecipe (Integer recipeId){
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new AppExceptions(ErrorCode.RECIPE_NOT_FOUND));

        String imageUrl = baseUrl + "/file/" + recipe.getImage();

        RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipe);
        recipeResponse.setImageUrl(imageUrl);

        return recipeResponse;
    }

    public List<RecipeResponse> getAllRecipe (){

        List<Recipe> recipes = recipeRepository.findAll();

        return recipeMapper.toRecipeResponseList(recipes);
    }


}
