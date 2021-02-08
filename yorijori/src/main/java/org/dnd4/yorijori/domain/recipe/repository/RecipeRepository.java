package org.dnd4.yorijori.domain.recipe.repository;

import java.util.List;

import org.dnd4.yorijori.domain.recipe.entity.Recipe;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

	@Query(value = "SELECT * FROM recipe WHERE time = :timeRange LIMIT :limit OFFSET :offset", nativeQuery = true)
	List<Recipe> findByTime(@Param("timeRange") int timeRange, @Param("limit") int limit, @Param("offset") int offset);

	@Query(value = "SELECT * FROM recipe WHERE step = :step LIMIT :limit OFFSET :offset", nativeQuery = true)
	List<Recipe> findByStep(@Param("step") int step, @Param("limit") int limit, @Param("offset") int offset);

	@Query(value = "SELECT * FROM recipe ORDER BY view_count DESC LIMIT :limit", nativeQuery = true)
	List<Recipe> viewCountDesc(@Param("limit") int limit);

	List<Recipe> findByviewCountGreaterThan(int viewCount, Pageable pageable);
	List<Recipe> findBytitleContaining(String keyword, Pageable pageable);
}
