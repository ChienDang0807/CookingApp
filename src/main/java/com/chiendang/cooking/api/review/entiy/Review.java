package com.chiendang.cooking.api.review.entiy;

import com.chiendang.cooking.api.auth.entity.User;
import com.chiendang.cooking.api.recipe.entity.AbstractEntity;
import com.chiendang.cooking.api.recipe.entity.Recipe;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review  {

    @EmbeddedId
    ReviewId id;

    @Column(name = "comment_content")
    String comment;

    @Column(name = "rating")
    Integer rating;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    private Recipe recipe;



}

