package org.dnd4.yorijori.domain.recipe.controller;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.dnd4.yorijori.domain.recipe.dto.ResponseDto;
import org.dnd4.yorijori.domain.recipe.service.RecipeListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RecipeListController {

	private final RecipeListService recipeListService;

	@GetMapping("/test")
	public List<ResponseDto> findById(@RequestParam(required = false) String id,
			@RequestParam(required = false) String step, @RequestParam(required = false) String time,
			@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate,
			@RequestParam(required = false) String order, @RequestParam(required = false) String keyword) {
		LocalDateTime start = null;
		LocalDateTime end = null;
		if (startDate != null) {
			start = LocalDateTime.parse(startDate + " 00:00:00",
					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		}
		if (endDate != null) {
			end = LocalDateTime.parse(endDate + " 23:59:59",
					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		}
		return recipeListService.findAll(id, step, time, start, end, order, keyword);
	}

	@GetMapping("/recipes")
	public List<ResponseDto> recipeList(@RequestParam(required = false) String queryType,
			@RequestParam(required = false) String keyword,
			@RequestParam(required = false, defaultValue = "10") int limit,
			@RequestParam(required = false, defaultValue = "0") int offset,
			@RequestParam(required = false, defaultValue = "100") String timeRange,
			@RequestParam(required = false) String step,
			@RequestParam(required = false, defaultValue = "2000-01-01") String startDate,
			@RequestParam(required = false, defaultValue = "2100-01-01") String endDate,
			@RequestParam(required = false, defaultValue = "latest") String order) throws ParseException {
		List<ResponseDto> result = new ArrayList<ResponseDto>();

		LocalDateTime start = LocalDateTime.parse(startDate + " 23:59:59",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime end = LocalDateTime.parse(endDate + " 23:59:59",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		if (queryType == null) {
			result = recipeListService.findAll(limit, offset, order, start, end, Integer.parseInt(timeRange));
		} else if (queryType.equals("search")) {
			result = recipeListService.searchRecipes(keyword, limit, offset, order);
		} else if (queryType.equals("time")) {
			result = recipeListService.timeRecipes(Integer.parseInt(timeRange), limit, offset);
		} else if (queryType.equals("step")) {
			result = recipeListService.stepRecipes(Integer.parseInt(step), limit, offset);
		} else if (queryType.equals("label")) {
			result = recipeListService.labelCountDesc(limit, startDate, endDate);
		}
		return result;
	}
}
