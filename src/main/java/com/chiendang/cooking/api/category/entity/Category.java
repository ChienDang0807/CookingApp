package com.chiendang.cooking.api.category.entity;

import com.chiendang.cooking.api.recipe.entity.Recipe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "category_recipe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "category_name")
    String name;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "category")
    @JsonIgnore
    List<Recipe> recipe = new ArrayList<>();

}
