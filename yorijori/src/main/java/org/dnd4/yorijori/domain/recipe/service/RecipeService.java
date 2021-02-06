package org.dnd4.yorijori.domain.recipe.service;

import org.dnd4.yorijori.domain.recipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecipeService {
	
	private final RecipeRepository recipeRepository;

}
