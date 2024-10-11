package com.chiendang.cooking.api.recipe;

import com.chiendang.cooking.api.auth.dto.response.ResponseData;
import com.chiendang.cooking.api.recipe.dto.request.RecipeRequest;
import com.chiendang.cooking.api.recipe.dto.response.RecipeResponse;
import com.chiendang.cooking.api.recipe.service.RecipeService;
import com.chiendang.cooking.exception.AppExceptions;
import com.chiendang.cooking.exception.ErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/recipe")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeController {
    RecipeService recipeService;


    @PostMapping("/add-recipe")
    public ResponseData<RecipeResponse> addMovie(@RequestPart MultipartFile file,
                                                 @RequestPart String recipeRequest) throws IOException {
        if(file.isEmpty()){ throw  new AppExceptions(ErrorCode.FILE_EMPTY);}

        RecipeRequest dto = convertToRecipeRequest(recipeRequest);
        return  new ResponseData<>(HttpStatus.CREATED.value(), "Thêm công thuc thanh cong !!", recipeService.addRecipe(dto,file));
    }

    // chuyển 1 chuỗi String sang Json
    private RecipeRequest convertToRecipeRequest(String recipeRequest) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return  objectMapper.readValue(recipeRequest, RecipeRequest.class);

    }
}
