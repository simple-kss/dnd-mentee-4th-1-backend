package org.dnd4.yorijori.domain.monthly_view.controller;

import java.util.Optional;

import org.dnd4.yorijori.domain.monthly_view.entity.MonthlyView;
import org.dnd4.yorijori.domain.monthly_view.repository.MonthlyViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class MonthlyViewController {

	@Autowired
	private MonthlyViewRepository monthlyViewRepository;
	
	@GetMapping
	public void test(@RequestParam(value = "recipe") Long recipe_id) {
		Optional<MonthlyView> optional = monthlyViewRepository.findByrecipeId(recipe_id);
		if(optional.isPresent()) {
			MonthlyView entity = optional.get();
			entity.increase();
			monthlyViewRepository.save(entity);
		} else {
			MonthlyView entity = new MonthlyView();
			entity.setRecipeId(recipe_id);
			entity.setCount(0);
			monthlyViewRepository.save(entity);
		}
	}
	
}
