package org.dnd4.yorijori.domain.monthly_view.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.dnd4.yorijori.domain.recipe.entity.Recipe;
import org.dnd4.yorijori.domain.recipe.service.RecipeService;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/view")
public class MonthlyViewController {

	@Resource(name = "redisTemplate")
	private ZSetOperations<String, Long> zSetOperations;
	private final RecipeService recipeService;
	
	@PostMapping
	public void increase(@RequestParam(value = "recipe") Long recipe_id) {
		zSetOperations.incrementScore("view", recipe_id, 1);
	}

	@GetMapping("/rank")
	public List<Recipe> viewTop(@RequestParam(value = "top") int top) {
		List<Recipe> result = new ArrayList<>();
		Set<Long> recipes = zSetOperations.reverseRange("view", 0, top - 1);
		for (Long rid : recipes) {
			result.add(recipeService.get(rid));
		}
		return result;
	}
}
