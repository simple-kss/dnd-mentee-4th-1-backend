package org.dnd4.yorijori.domain.recipe.service;

import java.util.List;
import java.util.stream.Collectors;

import org.dnd4.yorijori.domain.recipe.dto.ResponseDto;
import org.dnd4.yorijori.domain.recipe.repository.RecipeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecipeListService {
	private final RecipeRepository recipeRepository;

	public List<ResponseDto> findAll(int limit, int offset) {
		List<ResponseDto> result = recipeRepository.findAll(PageRequest.of(offset, limit)).stream()
				.map(ResponseDto::new).collect(Collectors.toList());
		return result;
	}

	public List<ResponseDto> searchRecipes(String keyword, int limit, int offset) {
		List<ResponseDto> result = recipeRepository.findByTitleContaining(keyword, limit, offset).stream()
				.map(ResponseDto::new).collect(Collectors.toList());
		return result;
	}
	
	public List<ResponseDto> timeRecipes(int timeRange, int limit, int offset){
		List<ResponseDto> result = recipeRepository.findByTime(timeRange, limit, offset).stream()
				.map(ResponseDto::new).collect(Collectors.toList());
		return result;
	}
	
	public List<ResponseDto> stepRecipes(int step, int limit, int offset){
		List<ResponseDto> result = recipeRepository.findByStep(step, limit, offset).stream()
				.map(ResponseDto::new).collect(Collectors.toList());
		return result;
	}
}
