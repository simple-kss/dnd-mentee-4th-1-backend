package org.dnd4.yorijori.domain.recipe.service;

import lombok.RequiredArgsConstructor;
import org.dnd4.yorijori.domain.comment.repository.CommentRepository;
import org.dnd4.yorijori.domain.common.YesOrNo;
import org.dnd4.yorijori.domain.ingredient.entity.Ingredient;
import org.dnd4.yorijori.domain.ingredient.repository.IngredientRepository;
import org.dnd4.yorijori.domain.label.entity.Label;
import org.dnd4.yorijori.domain.label.repository.LabelRepository;
import org.dnd4.yorijori.domain.rating.repository.RatingRepository;
import org.dnd4.yorijori.domain.recipe.dto.RequestDto;
import org.dnd4.yorijori.domain.recipe.dto.UpdateRequestDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
    public Long add(RequestDto requestDto){

        User user = userRepository.getOne(requestDto.getWriterId());

        List<Ingredient> mainIngredients = ingredientRepository.findAllById(requestDto.getMainIngredientIds());
        List<RecipeIngredient> mainRecipeIngredients = mainIngredients.stream()
                .map(i->RecipeIngredient.builder()
                        .ingredient(i)
                        .isSub(YesOrNo.N).build())
                .collect(Collectors.toList());

        List<RecipeIngredient> recipeIngredients = new ArrayList<>(mainRecipeIngredients);
//        recipeIngredientRepository.saveAll(mainRecipeIngredients);


        if(requestDto.getSubIngredientIds() != null){
            List<Ingredient> subIngredients = ingredientRepository.findAllById(requestDto.getSubIngredientIds());
            List<RecipeIngredient> subRecipeIngredients = subIngredients.stream()
                            .map(i->RecipeIngredient.builder()
                                    .ingredient(i)
                                    .isSub(YesOrNo.Y).build())
                            .collect(Collectors.toList());

            //recipeIngredientRepository.saveAll(subRecipeIngredients);
            recipeIngredients.addAll(subRecipeIngredients);
        }


        List<Theme> themes = themeRepository.findAllById(requestDto.getThemeIds());
        List<RecipeTheme> recipeThemes = themes.stream()
                .map(t-> RecipeTheme.builder()
                        .theme(t)
                        .build())
                .collect(Collectors.toList());

        //recipeThemeRepository.saveAll(recipeThemes);

        AtomicInteger sequence = new AtomicInteger();
        List<Step> steps = requestDto.getSteps().stream()
                .map((s)->Step.builder()
                        .description(s.getDescription())
                        .imageUrl(s.getImage())
                        .sequence(sequence.getAndIncrement())
                        .build())
                .collect(Collectors.toList());

//        stepRepository.saveAll(
//                steps
//        );


        Recipe recipe = Recipe.builder()
                .title(requestDto.getTitle())
                .step(requestDto.getSteps().size())
                .time(requestDto.getTime())
                .thumbnail(requestDto.getThumbnail())
                .user(user)
                .recipeIngredients(recipeIngredients)
                .recipeThemes(recipeThemes)
                .steps(steps)
                .build();

        recipeRepository.save(recipe);
        return recipe.getId();
//        return recipeRepository.getOne(recipe.getId());
    }

    @Transactional
    public Long update(Long id, UpdateRequestDto updateRequestDto){

        //recipe 수정
        Recipe recipe = recipeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 아이디의 레시피가 없습니다. id : " + id));


        List<Ingredient> mainIngredients = ingredientRepository.findAllById(updateRequestDto.getMainIngredientIds());
        List<RecipeIngredient> mainRecipeIngredients = mainIngredients.stream()
                .map(i->RecipeIngredient.builder()
                        .ingredient(i)
                        .isSub(YesOrNo.N).build())
                .collect(Collectors.toList());

        List<RecipeIngredient> recipeIngredients = new ArrayList<>(mainRecipeIngredients);

        if(updateRequestDto.getSubIngredientIds() != null){
            List<Ingredient> subIngredients = ingredientRepository.findAllById(updateRequestDto.getSubIngredientIds());
            List<RecipeIngredient> subRecipeIngredients = subIngredients.stream()
                    .map(i->RecipeIngredient.builder()
                            .ingredient(i)
                            .isSub(YesOrNo.Y).build())
                    .collect(Collectors.toList());

            recipeIngredients.addAll(subRecipeIngredients);
        }


        List<Theme> themes = themeRepository.findAllById(updateRequestDto.getThemeIds());
        List<RecipeTheme> recipeThemes = themes.stream()
                .map(t-> RecipeTheme.builder()
                        .theme(t)
                        .build())
                .collect(Collectors.toList());


        //step update
        updateRequestDto.getSteps().forEach(stepDto -> {

            Step step = stepRepository.getOne(stepDto.getId());
            step.update(recipe , stepDto.getDescription(), stepDto.getImageUrl(), stepDto.getSequence());
        });

        recipe.update(updateRequestDto.getTitle(),
                updateRequestDto.getSteps().size(),
                updateRequestDto.getTime(),
                updateRequestDto.getViewCount(),
                updateRequestDto.getThumbnail(),
                recipeIngredients,
                recipeThemes
        );

        return id;
    }

    @Transactional
    public void delete(Long id){
        Recipe recipe = recipeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 아이디의 레시피가 없습니다. id : " + id));
        recipeRepository.delete(recipe);
    }
}
