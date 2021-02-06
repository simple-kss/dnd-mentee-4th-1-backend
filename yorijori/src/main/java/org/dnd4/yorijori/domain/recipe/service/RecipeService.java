package org.dnd4.yorijori.domain.recipe.service;

import lombok.RequiredArgsConstructor;
import org.dnd4.yorijori.domain.recipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
}
