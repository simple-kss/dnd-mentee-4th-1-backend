package org.dnd4.yorijori.service;

import org.dnd4.yorijori.domain.ingredient.entity.Ingredient;
import org.dnd4.yorijori.domain.ingredient.repository.IngredientRepository;
import org.dnd4.yorijori.domain.recipe.dto.RequestDto;
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
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

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
        RequestDto requestDto = new RequestDto();
        requestDto.setTitle("title");
        requestDto.setThumbnail("http://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg");

        List<Long> mainIngredientIds = new ArrayList<>();

        Ingredient ingredient = Ingredient.builder().name("재료").build();
        Long ingredientId = ingredientRepository.save(ingredient).getId();
        mainIngredientIds.add(ingredientId);
        requestDto.setMainIngredientIds(mainIngredientIds);

        List<Long> themeIds = new ArrayList<>();
        Theme theme = Theme.builder().name("분위기").build();
        Long themeId = themeRepository.save(theme).getId();
        themeIds.add(themeId);
        requestDto.setThemeIds(themeIds);

        RequestDto.Step step = new RequestDto.Step();
        step.setDescription("des");
        step.setImage("http://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg");

        List<RequestDto.Step> steps = new ArrayList<>();
        steps.add(step);
        requestDto.setSteps(steps);

        requestDto.setTime(15);

        User user = User.builder().name("soob").email("sjklf@slkj.com").build();

        Long userId = userRepository.save(user).getId();
        requestDto.setWriterId(userId);

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

    }

}
