package com.chiendang.cooking.api.favorite_recipe.entity;

import com.chiendang.cooking.api.auth.entity.User;
import com.chiendang.cooking.api.recipe.entity.Recipe;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Table(name = "favorite_recipe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FavoriteRecipe {

    @EmbeddedId
    FavoriteRecipeId id;

    @Column(name = "is_Favorite")
    Boolean isFavorite;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    private Recipe recipe;


}