package com.chiendang.cooking.api.recipe;

import com.chiendang.cooking.api.auth.dto.response.ResponseData;
import com.chiendang.cooking.api.auth.dto.response.ResponseError;
import com.chiendang.cooking.api.recipe.dto.request.RecipeRequest;
import com.chiendang.cooking.api.recipe.dto.response.PageResponse;
import com.chiendang.cooking.api.recipe.service.RecipeService;
import com.chiendang.cooking.exception.AppExceptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
@Tag(name = "recipe")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeController {
    RecipeService recipeService;

//    @Operation(method = "POST", summary = "Add new user", description = "Send a request via this API to create new user")
//    @PostMapping
//    public ResponseData<RecipeResponse> uploadFile(@RequestPart MultipartFile file,@RequestPart RecipeRequest dto) throws IOException {
//        if(file.isEmpty()){ throw  new AppExceptions(ErrorCode.FILE_EMPTY);}
//
//        return  new ResponseData<>(HttpStatus.CREATED.value(), "Thêm công thuc thanh cong !!", recipeService.addRecipe(dto,file));
//    }


    @Operation(method = "GET", summary = "Get recipe limit", description = "Send a request via this API to get user")
    @GetMapping
    public ResponseData<PageResponse<?>> findRecipeByLimit(@RequestParam(value = "lastId", defaultValue = "0") long lastId,
                                                           @Min(5) @RequestParam(value = "limit" , defaultValue = "10") int limit){
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Lấy danh sách thành công", recipeService.findRecipeByLimit(lastId,limit) );
        }catch (AppExceptions e){
            return  new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    // chuyển 1 chuỗi String sang Json
    private RecipeRequest convertToRecipeRequest(String recipeRequest) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return  objectMapper.readValue(recipeRequest, RecipeRequest.class);

    }
}
