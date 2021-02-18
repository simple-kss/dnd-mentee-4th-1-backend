package org.dnd4.yorijori.domain.monthly_view.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.dnd4.yorijori.domain.monthly_view.service.MonthlyViewService;
import org.dnd4.yorijori.domain.recipe.entity.Recipe;
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

	private final MonthlyViewService monthlyViewService;
	
	@PostMapping
	public void visit(@RequestParam(value = "recipe") Long recipe_id) {
		monthlyViewService.visit(recipe_id);
	}

	@GetMapping("/rank")
	public List<Recipe> rank(@RequestParam(value = "top") int top) {
		return monthlyViewService.rank(top);
	}
}
