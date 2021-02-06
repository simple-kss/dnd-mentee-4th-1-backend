package org.dnd4.yorijori.domain.recipe.repository;

import java.util.List;

import org.dnd4.yorijori.domain.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	@Query("select r from Recipe r where r.title like ?1")
	List<Recipe> findAllByTitleLike(String title);

	List<Recipe> findByTitleContaining(String title);
}
