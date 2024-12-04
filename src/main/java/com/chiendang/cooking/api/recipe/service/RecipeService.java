package com.chiendang.cooking.api.recipe.service;

import com.chiendang.cooking.api.auth.dto.response.UserResponse;
import com.chiendang.cooking.api.category.mapper.CategoryMapper;
import com.chiendang.cooking.api.ingredient.entity.Ingredient;
import com.chiendang.cooking.api.ingredient.mapper.IngredientMapper;
import com.chiendang.cooking.api.instruction.entity.Instruction;
import com.chiendang.cooking.api.instruction.mapper.InstructionMapper;
import com.chiendang.cooking.api.recipe.dto.request.RecipeRequest;
import com.chiendang.cooking.api.recipe.dto.response.PageResponse;
import com.chiendang.cooking.api.recipe.dto.response.RecipeResponse;
import com.chiendang.cooking.api.recipe.entity.Recipe;
import com.chiendang.cooking.api.recipe.mapper.RecipeMapper;
import com.chiendang.cooking.api.recipe.respository.RecipeRepository;
import com.chiendang.cooking.exception.AppExceptions;
import com.chiendang.cooking.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeService {

    RecipeRepository recipeRepository;


    RecipeMapper recipeMapper;
    InstructionMapper instructionMapper;
    IngredientMapper ingredientMapper;
    CategoryMapper categoryMapper;


    @NonFinal
    @Value("${project.foodimage}")
    String path;

    @NonFinal
    @Value("${base.url}")
    String baseUrl;


//    @PreAuthorize("hasRole('USER')")
//    public RecipeResponse addRecipe(RecipeRequest request, MultipartFile file) throws IOException {
//        if (Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))){
//            throw  new AppExceptions(ErrorCode.FILE_IS_EXISTED);
//        }
//        String uploadedFileName = fileService.uploadFile(path,file);
//        //2. set the value of field 'poster' as filename
//        request.setImage(uploadedFileName);
//        // lay thong tin nguoi dung hien tai
//        var context = SecurityContextHolder.getContext();
//        String name= context.getAuthentication().getName();
//        User user = userRespository.findByEmail(name)
//                .orElseThrow(() -> new AppExceptions(ErrorCode.USER_NOT_EXISTED));
//
//        Recipe recipe = Recipe.builder()
//                .recipeName(request.getRecipeName())
//                .prepTime(request.getPrepTime())
//                .cookTime(request.getCookTime())
//                .category(request.getCategory())
//                .userCreated(user)
//                .image(request.getImage())
//                .build();
//
//        Recipe savedRecipe =  recipeRepository.save(recipe);
//        try{
//            request.getIngredients().forEach(ingredientService::addIngredient);
//            request.getInstructions().forEach(instructionService::addInstruction);
//        } catch (Exception e) {
//            throw new RuntimeException("Error saving recipe details", e);
//        }
//
//        savedRecipe.setInstructions(instructionMapper.toInstructionList(request.getInstructions()));
//        savedRecipe.setIngredients(ingredientMapper.toIngredientList(new HashSet<>(request.getIngredients())));
//
//        String imageUrl = baseUrl + "/file/" +uploadedFileName;
//
//        RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(savedRecipe);
//
//        recipeResponse.setImageUrl(imageUrl);
//
//        return  recipeResponse;
//    }

    public RecipeResponse getRecipe (Integer recipeId){
        Recipe recipe = this.getRecipeInfo(recipeId);

        String imageUrl = baseUrl + "/file/" + recipe.getImage();

        RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipe);
        recipeResponse.setImageUrl(imageUrl);

        return recipeResponse;
    }

//    public RecipeResponse updateRecipe(Integer id, RecipeRequest request, MultipartFile file) throws IOException {
//        Recipe recipe = recipeRepository.findById(id)
//                .orElseThrow(() -> new AppExceptions(ErrorCode.RESOURCES_NOT_FOUND));
//
//        String fileName = recipe.getImage();
//        if (file != null){
//            // xoa file lien quan
//            Files.deleteIfExists(Paths.get(path + File.separator +recipe.getImage() ));
//            if (Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))){
//                throw  new AppExceptions(ErrorCode.FILE_IS_EXISTED);
//            }
//            fileName=fileService.uploadFile(path,file);
//        }
//
//        request.setImage(fileName);
//
//        Recipe recipeUpdate = recipeMapper.toRecipe(request);
//
//        recipeRepository.save(recipeUpdate);
//
//        String imageUrl = baseUrl + "/file/" + fileName;
//
//        RecipeResponse recipeResponse = recipeMapper.toRecipeResponse(recipeUpdate);
//
//        recipeResponse.setImageUrl(imageUrl);
//
//        return  recipeResponse;
//
//    }

    public List<RecipeResponse> getAllRecipe (){

        List<Recipe> recipes = recipeRepository.findAll();

        return recipes.stream().map(recipeMapper::toRecipeResponse).toList();
    }

    public PageResponse<?> findRecipeByLimit (String name, long lastId , int limit) {
        List<Recipe> recipes;


        if(StringUtils.hasLength(name)){
            if (lastId == 0) {
                // Gọi lần đầu với lastId = 0
                recipes = recipeRepository.findRecipeByName(name,0L, limit);
            } else {
                // Lần sau, truyền lastId là id của bản ghi cuối cùng trong list
                recipes = recipeRepository.findRecipeByName(name,lastId, limit);
            }
        }else {
            if (lastId == 0) {
                // Gọi lần đầu với lastId = 0
                recipes = recipeRepository.findRecipesByIdGreaterThan(0L, limit);
            } else {
                // Lần sau, truyền lastId là id của bản ghi cuối cùng trong list
                recipes = recipeRepository.findRecipesByIdGreaterThan(lastId, limit);
            }

        }

        long newLastId = 0;  // Khởi tạo giá trị mặc định cho lastId
        if (recipes != null && !recipes.isEmpty()) {
            // Lấy ID của phần tử cuối cùng trong danh sách
            newLastId = recipes.get(recipes.size() - 1).getId();
        } else {
            throw new AppExceptions(ErrorCode.LIST_EMPTY);
        }

        List<RecipeResponse> recipeResponses = toRecipeResponse(recipes);

        return  PageResponse.builder()
                .lastElementId((int)newLastId)
                .items(recipeResponses)
                .build();

    }


     Recipe getRecipeInfo( Integer recipeId){
         return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new AppExceptions(ErrorCode.RESOURCES_NOT_FOUND));
    }

    public boolean existById(Integer recipeId){
        return !recipeRepository.existsById(recipeId);
    }

    public List<RecipeResponse> toRecipeResponse(List<Recipe> recipes){
        List<RecipeResponse> recipeResponses = new ArrayList<>();



        for (Recipe recipe:recipes){
            Set<Ingredient> ingredients = recipe.getIngredients();
            List<Instruction> instructions = recipe.getInstructions();

            //imageUrl
            String fileName = recipe.getImage();
            String imageUrl = baseUrl + "/file/" + fileName;


            RecipeResponse response = new RecipeResponse();
            response.setImageUrl(imageUrl);
            response.setRecipeName(recipe.getRecipeName());
            response.setId(recipe.getId());
            response.setCategory(categoryMapper.toCategoryResponse(recipe.getCategory()));
            response.setUser(new UserResponse(recipe.getUserCreated().getFirstName(), recipe.getUserCreated().getLastName()));
            response.setImage(fileName);
            response.setCookTime(recipe.getCookTime());
            response.setPrepTime(recipe.getPrepTime());
            response.setIngredients(ingredients.stream().map(ingredientMapper::toInIngredientResponse).collect(Collectors.toSet()));
            response.setInstructions(instructions.stream().map(instructionMapper::toInstructionResponse).toList());
            recipeResponses.add(response);
        }
        return recipeResponses;
    }




}
