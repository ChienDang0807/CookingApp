package com.chiendang.cooking.api.category.mapper;

import com.chiendang.cooking.api.category.dto.CategoryRequest;
import com.chiendang.cooking.api.category.dto.CategoryResponse;
import com.chiendang.cooking.api.category.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryRequest request);

    CategoryResponse toCategoryResponse(Category category);

}
