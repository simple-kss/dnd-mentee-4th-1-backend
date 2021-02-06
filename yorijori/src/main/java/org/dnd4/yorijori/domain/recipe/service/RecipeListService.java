package org.dnd4.yorijori.domain.recipe.service;

import java.util.List;
import java.util.stream.Collectors;

import org.dnd4.yorijori.domain.recipe.dto.RecipeListDto;
import org.dnd4.yorijori.domain.recipe.repository.RecipeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecipeListService {
	private final RecipeRepository recipeRepository;

	public List<RecipeListDto> findAll() {
		List<RecipeListDto> result = recipeRepository.findAll(PageRequest.of(1, 2)).stream().map(RecipeListDto::new)
				.collect(Collectors.toList());
		return result;
	}

	public List<RecipeListDto> searchRecipes(String keyword) {
		List<RecipeListDto> result = recipeRepository.findByTitleContaining(keyword).stream().map(RecipeListDto::new)
				.collect(Collectors.toList());
		return result;
	}
}
