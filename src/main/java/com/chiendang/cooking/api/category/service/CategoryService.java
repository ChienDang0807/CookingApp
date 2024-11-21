package com.chiendang.cooking.api.category.service;

import com.chiendang.cooking.api.category.dto.CategoryRequest;
import com.chiendang.cooking.api.category.dto.CategoryResponse;
import com.chiendang.cooking.api.category.entity.Category;
import com.chiendang.cooking.api.category.mapper.CategoryMapper;
import com.chiendang.cooking.api.category.repository.CategoryRepository;
import com.chiendang.cooking.api.recipe.entity.Recipe;
import com.chiendang.cooking.api.recipe.mapper.RecipeMapper;
import com.chiendang.cooking.api.recipe.dto.request.RecipeRequest;

import com.chiendang.cooking.exception.AppExceptions;
import com.chiendang.cooking.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    public CategoryResponse addCategory(CategoryRequest request){
        if (categoryRepository.existsByName(request.getName())){
            throw  new AppExceptions(ErrorCode.CATEGORY_IS_EXISTED);
        }
        Category category  = categoryRepository.save(Category.builder()
                .name(request.getName()).build());

        return  CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public CategoryResponse updateCategory(Integer id, CategoryRequest request){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppExceptions(ErrorCode.RESOURCES_NOT_FOUND));

        category.setName(request.getName());

        categoryRepository.save(category);

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();

    }

    public void deleteCategory(Integer categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new AppExceptions(ErrorCode.RESOURCES_NOT_FOUND));

        categoryRepository.delete(category);
    }

    public List<CategoryResponse> findAllCategories (){
        List<Category> categories =  categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toCategoryResponse).collect(Collectors.toList());
    }

    public List<CategoryResponse> findAllCategoriesByName (String name){
        List<Category> categories = categoryRepository.findByNameContainingIgnoreCase(name);
        return categories.stream().map(categoryMapper::toCategoryResponse).collect(Collectors.toList());
    }


}
