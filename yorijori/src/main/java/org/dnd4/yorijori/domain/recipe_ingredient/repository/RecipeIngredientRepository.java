package org.dnd4.yorijori.domain.recipe_ingredient.repository;

import org.dnd4.yorijori.domain.recipe_ingredient.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
}
