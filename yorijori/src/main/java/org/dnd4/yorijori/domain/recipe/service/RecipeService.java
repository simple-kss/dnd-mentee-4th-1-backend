package org.dnd4.yorijori.domain.recipe.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.dnd4.yorijori.domain.comment.repository.CommentRepository;
import org.dnd4.yorijori.domain.common.YesOrNo;
import org.dnd4.yorijori.domain.ingredient.entity.Ingredient;
import org.dnd4.yorijori.domain.ingredient.repository.IngredientRepository;
import org.dnd4.yorijori.domain.label.repository.LabelRepository;
import org.dnd4.yorijori.domain.rating.repository.RatingRepository;
import org.dnd4.yorijori.domain.recipe.dto.RequestDto;
import org.dnd4.yorijori.domain.recipe.entity.Recipe;
import org.dnd4.yorijori.domain.recipe.repository.RecipeRepository;
import org.dnd4.yorijori.domain.recipe_ingredient.entity.RecipeIngredient;
import org.dnd4.yorijori.domain.recipe_ingredient.repository.RecipeIngredientRepository;
import org.dnd4.yorijori.domain.recipe_theme.entity.RecipeTheme;
import org.dnd4.yorijori.domain.recipe_theme.repository.RecipeThemeRepository;
import org.dnd4.yorijori.domain.step.entity.Step;
import org.dnd4.yorijori.domain.step.repository.StepRepository;
import org.dnd4.yorijori.domain.theme.entity.Theme;
import org.dnd4.yorijori.domain.theme.repository.ThemeRepository;
import org.dnd4.yorijori.domain.user.entity.User;
import org.dnd4.yorijori.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecipeService {
	private final RecipeRepository recipeRepository;

	private final RecipeIngredientRepository recipeIngredientRepository;
	private final IngredientRepository ingredientRepository;

	private final RecipeThemeRepository recipeThemeRepository;
	private final ThemeRepository themeRepository;

	private final StepRepository stepRepository;
	private final CommentRepository commentRepository;
	private final RatingRepository ratingRepository;
	private final LabelRepository labelRepository;
	private final UserRepository userRepository;

	@Transactional
	public Recipe add(RequestDto requestDto) {

		User user = userRepository.getOne(requestDto.getWriterId());

		Recipe recipe = Recipe.builder().title(requestDto.getTitle()).step(requestDto.getSteps().size())
				.time(requestDto.getTime()).thumbnail(requestDto.getThumbnail()).user(user).build();

		recipeRepository.save(recipe);

		List<Ingredient> mainIngredients = ingredientRepository.findAllById(requestDto.getMainIngredientIds());

		recipeIngredientRepository.saveAll(mainIngredients.stream()
				.map(i -> RecipeIngredient.builder().recipe(recipe).ingredient(i).isSub(YesOrNo.N).build())
				.collect(Collectors.toList()));

		List<Ingredient> subIngredients = ingredientRepository.findAllById(requestDto.getSubIngredientIds());
		recipeIngredientRepository.saveAll(subIngredients.stream()
				.map(i -> RecipeIngredient.builder().recipe(recipe).ingredient(i).isSub(YesOrNo.N).build())
				.collect(Collectors.toList()));

		List<Theme> themes = themeRepository.findAllById(requestDto.getThemeIds());

		recipeThemeRepository.saveAll(themes.stream().map(t -> RecipeTheme.builder().recipe(recipe).theme(t).build())
				.collect(Collectors.toList()));

		AtomicInteger sequence = new AtomicInteger();

		stepRepository.saveAll(requestDto
				.getSteps().stream().map((s) -> Step.builder().recipe(recipe).description(s.getDescription())
						.imageUrl(s.getImage()).sequence(sequence.getAndIncrement()).build())
				.collect(Collectors.toList()));

		return recipeRepository.getOne(recipe.getId());
	}
}
