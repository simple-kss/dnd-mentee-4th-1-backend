package org.dnd4.yorijori.domain.recipe.repository;

import java.util.List;

import org.dnd4.yorijori.domain.recipe.entity.Recipe;

public interface RecipeQuerydslRepoistory {
	
	List<Recipe> findById(int id);
}
