package org.dnd4.yorijori.service;

import org.dnd4.yorijori.domain.ingredient.entity.Ingredient;
import org.dnd4.yorijori.domain.ingredient.repository.IngredientRepository;
import org.dnd4.yorijori.domain.recipe.dto.RequestDto;
import org.dnd4.yorijori.domain.recipe.dto.StepDto;
import org.dnd4.yorijori.domain.recipe.dto.UpdateRequestDto;
import org.dnd4.yorijori.domain.recipe.entity.Recipe;
import org.dnd4.yorijori.domain.recipe.repository.RecipeRepository;
import org.dnd4.yorijori.domain.recipe.service.RecipeService;
import org.dnd4.yorijori.domain.theme.entity.Theme;
import org.dnd4.yorijori.domain.theme.repository.ThemeRepository;
import org.dnd4.yorijori.domain.user.entity.User;
import org.dnd4.yorijori.domain.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RecipeServiceTest {
    @Autowired
    RecipeService recipeService;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    ThemeRepository themeRepository;
    @Test
    public void 레시피등록() throws Exception{


        //given

        List<Long> mainIngredientIds = new ArrayList<>();

        Ingredient ingredient = Ingredient.builder().name("재료").build();
        Long ingredientId = ingredientRepository.save(ingredient).getId();
        mainIngredientIds.add(ingredientId);

        List<Long> themeIds = new ArrayList<>();
        Theme theme = Theme.builder().name("분위기").build();
        Long themeId = themeRepository.save(theme).getId();
        themeIds.add(themeId);

        RequestDto.Step step = new RequestDto.Step();
        step.setDescription("des");
        step.setImage("http://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg");

        List<RequestDto.Step> steps = new ArrayList<>();
        steps.add(step);

        User user = User.builder().name("soob").email("sjklf@slkj.com").build();

        Long userId = userRepository.save(user).getId();

        RequestDto requestDto = new RequestDto("title",
                "http://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg",
                mainIngredientIds, null, themeIds, steps, 15, userId
                );


        //when
        Long savedId = recipeService.add(requestDto);

        //then
        Recipe savedRecipe = recipeRepository.getOne(savedId);

        assertEquals(savedRecipe.getTitle(), requestDto.getTitle());


        assertArrayEquals(
                savedRecipe.getMainIngredients().stream()
                .map(Ingredient::getId)
                .mapToLong(Long::longValue)
                .toArray()
                ,requestDto.getMainIngredientIds().stream()
                        .mapToLong(Long::longValue)
                        .toArray()
                );

        assertArrayEquals(
                savedRecipe.getThemes().stream()
                .map(Theme::getId)
                .mapToLong(Long::longValue)
                .toArray(),
                requestDto.getThemeIds().stream()
                .mapToLong(Long::longValue)
                .toArray()
        );

    }

    @Test
    public void 레시피수정() throws Exception{
        //given
        List<Long> mainIngredientIds = new ArrayList<>();

        Ingredient ingredient = Ingredient.builder().name("재료1").build();
        Long ingredientId = ingredientRepository.save(ingredient).getId();


        mainIngredientIds.add(ingredientId);

        List<Long> themeIds = new ArrayList<>();
        Theme theme = Theme.builder().name("분위기").build();
        Long themeId = themeRepository.save(theme).getId();
        themeIds.add(themeId);

        RequestDto.Step step = new RequestDto.Step();
        step.setDescription("des");
        step.setImage("http://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg");

        List<RequestDto.Step> steps = new ArrayList<>();
        steps.add(step);

        User user = User.builder().name("soob").email("sjklf@slkj.com").build();

        Long userId = userRepository.save(user).getId();

        RequestDto requestDto = new RequestDto("title",
                "http://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg",
                mainIngredientIds, null, themeIds, steps, 15, userId
        );

        Long savedId = recipeService.add(requestDto);
        Recipe savedRecipe = recipeRepository.getOne(savedId);

        //when

        String updateTitle = "updateTitle";
        String updateThumbnail = "updateThumbnail";

        Ingredient ingredient2 = Ingredient.builder().name("재료2").build();
        Long ingredientId2 = ingredientRepository.save(ingredient2).getId();

        mainIngredientIds.add(ingredientId2);

        Theme theme2 = Theme.builder().name("분위기2").build();
        Long themeId2 = themeRepository.save(theme2).getId();

        themeIds.remove(0);
        themeIds.add(themeId2);


        String updateDescription = "updateDescription";
        StepDto stepDto = new StepDto(savedRecipe.getSteps().get(0).getId(), updateDescription, "updateUrl", 0);

        List<StepDto> stepDtos = new ArrayList<>();
        stepDtos.add(stepDto);
        UpdateRequestDto updateRequestDto = new UpdateRequestDto(updateTitle, updateThumbnail, mainIngredientIds, null,themeIds, stepDtos,5, 100);

        Long updatedId = recipeService.update(savedId, updateRequestDto);

        Recipe updatedRecipe = recipeRepository.getOne(updatedId);

        //then
        assertEquals(updatedRecipe.getTitle(), updateRequestDto.getTitle());
    }
    @Test
    public void 레시피삭제() throws Exception{
        //givne
        List<Long> mainIngredientIds = new ArrayList<>();

        Ingredient ingredient = Ingredient.builder().name("재료1").build();
        Long ingredientId = ingredientRepository.save(ingredient).getId();


        mainIngredientIds.add(ingredientId);

        List<Long> themeIds = new ArrayList<>();
        Theme theme = Theme.builder().name("분위기").build();
        Long themeId = themeRepository.save(theme).getId();
        themeIds.add(themeId);

        RequestDto.Step step = new RequestDto.Step();
        step.setDescription("des");
        step.setImage("http://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg");

        List<RequestDto.Step> steps = new ArrayList<>();
        steps.add(step);

        User user = User.builder().name("soob").email("sjklf@slkj.com").build();

        Long userId = userRepository.save(user).getId();

        RequestDto requestDto = new RequestDto("title",
                "http://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg",
                mainIngredientIds, null, themeIds, steps, 15, userId
        );

        Long savedId = recipeService.add(requestDto);
        Recipe savedRecipe = recipeRepository.getOne(savedId);

        //when
        recipeService.delete(savedId);

        Recipe deletedRecipe = recipeRepository.findById(savedId).orElseThrow(()->new IllegalArgumentException("해당 아이디의 레시피가 없습니다. id : " + savedId));

        //then

      
        fail("예외발생");
    }
}
