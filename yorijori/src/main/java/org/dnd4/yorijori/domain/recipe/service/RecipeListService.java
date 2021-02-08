package org.dnd4.yorijori.domain.recipe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.dnd4.yorijori.domain.label.repository.LabelRepository;
import org.dnd4.yorijori.domain.recipe.dto.ResponseDto;
import org.dnd4.yorijori.domain.recipe.entity.Recipe;
import org.dnd4.yorijori.domain.recipe.repository.RecipeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecipeListService {
	private final RecipeRepository recipeRepository;
	private final LabelRepository labelRepository;

	public List<ResponseDto> findAll(int limit, int offset, String order) {
		List<ResponseDto> result = new ArrayList<ResponseDto>();
		if (order.equals("view")) {
			Sort sort = Sort.by(Sort.Direction.DESC, "viewCount");
			Pageable pageable = PageRequest.of(offset, limit, sort);
			result = recipeRepository.findAll(pageable).stream().map(ResponseDto::new)
					.collect(Collectors.toList());
		}
		if (order.equals("latest")) {
			Sort sort = Sort.by(Sort.Direction.DESC, "id");
			Pageable pageable = PageRequest.of(offset, limit, sort);
			result = recipeRepository.findAll(pageable).stream().map(ResponseDto::new)
					.collect(Collectors.toList());
		}
		return result;
	}

	public List<ResponseDto> searchRecipes(String keyword, int limit, int offset, String order) {
		List<ResponseDto> result = recipeRepository.findByTitleContaining(keyword, limit, offset, order).stream()
				.map(ResponseDto::new).collect(Collectors.toList());
		return result;
	}

	public List<ResponseDto> timeRecipes(int timeRange, int limit, int offset) {
		List<ResponseDto> result = recipeRepository.findByTime(timeRange, limit, offset).stream().map(ResponseDto::new)
				.collect(Collectors.toList());
		return result;
	}

	public List<ResponseDto> stepRecipes(int step, int limit, int offset) {
		List<ResponseDto> result = recipeRepository.findByStep(step, limit, offset).stream().map(ResponseDto::new)
				.collect(Collectors.toList());
		return result;
	}

	public List<ResponseDto> labelCountDesc(int limit, String startDate, String endDate) {
		List<Long> recipe_id = labelRepository.labelCountDesc(limit, startDate, endDate);
		List<Recipe> entity = new ArrayList<Recipe>();

		for (Long id : recipe_id) {
			Recipe recipe = recipeRepository.getOne(id);
			entity.add(recipe);
		}
		List<ResponseDto> result = entity.stream().map(ResponseDto::new).collect(Collectors.toList());

		return result;
	}
}
