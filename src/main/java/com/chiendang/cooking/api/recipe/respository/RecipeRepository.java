package com.chiendang.cooking.api.recipe.respository;

import com.chiendang.cooking.api.recipe.dto.response.RecipeResponse;
import com.chiendang.cooking.api.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Integer> {

    @Query(value = """
        SELECT * FROM recipe r
        ORDER BY r.recipe_id ASC 
        LIMIT :limit OFFSET :offset
        """, nativeQuery = true)
    List<Recipe> findAllRecipe(@Param("offset") int offSet, @Param("limit") int limit);

    // dung keyset pagination
    @Query(value = """
        SELECT * FROM recipe r
        WHERE r.recipe_id > :lastId
        ORDER BY r.recipe_id ASC
        LIMIT :limit
        """, nativeQuery = true)
    List<Recipe> findRecipesByIdGreaterThan(@Param("lastId") long lastId, @Param("limit") int limit);

    @Query("SELECT COUNT(r) FROM Recipe r")
    long count();

    Recipe findByid(Integer id);
}
