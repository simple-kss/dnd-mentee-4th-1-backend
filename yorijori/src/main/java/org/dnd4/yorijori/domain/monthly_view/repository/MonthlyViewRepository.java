package org.dnd4.yorijori.domain.monthly_view.repository;

import java.util.Optional;

import org.dnd4.yorijori.domain.monthly_view.entity.MonthlyView;
import org.springframework.data.repository.CrudRepository;

public interface MonthlyViewRepository extends CrudRepository<MonthlyView, Long> {
	
	public Optional<MonthlyView> findByrecipeId(Long recipe_id);
	
}
