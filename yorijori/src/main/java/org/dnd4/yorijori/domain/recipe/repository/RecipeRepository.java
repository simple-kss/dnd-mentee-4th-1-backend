package org.dnd4.yorijori.domain.recipe.repository;

import java.util.List;

import org.dnd4.yorijori.domain.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	List<Recipe> findByTitleContaining(String title);
}
