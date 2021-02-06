package org.dnd4.yorijori.domain.recipe.repository;

import java.util.List;

import org.dnd4.yorijori.domain.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	@Query("SELECT r FROM Recipe r WHERE r.title LIKE %:keyword%")
	List<Recipe> findByTitleContaining(@Param("keyword") String keyword);
}
