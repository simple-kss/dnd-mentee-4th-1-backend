package org.dnd4.yorijori.domain.recipe_ingredient.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dnd4.yorijori.domain.common.YesOrNo;
import org.dnd4.yorijori.domain.ingredient.entity.Ingredient;
import org.dnd4.yorijori.domain.recipe.entity.Recipe;
import org.dnd4.yorijori.domain.user.entity.User;


import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    private YesOrNo isSub;

    @Builder
    public RecipeIngredient(Recipe recipe, Ingredient ingredient, YesOrNo isSub){
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.isSub = isSub;
    }

}
