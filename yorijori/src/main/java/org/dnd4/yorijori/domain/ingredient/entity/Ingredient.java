package org.dnd4.yorijori.domain.ingredient.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dnd4.yorijori.domain.recipe.entity.Recipe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Recipe recipe;
    private String name;
    private String unit;
    private String quantity;
    @Builder
    public Ingredient(String name, String unit, String quantity){
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
