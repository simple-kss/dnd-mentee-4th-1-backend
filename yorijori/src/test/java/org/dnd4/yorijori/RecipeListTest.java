package org.dnd4.yorijori;

import static junit.framework.TestCase.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.dnd4.yorijori.domain.ingredient.entity.Ingredient;
import org.dnd4.yorijori.domain.ingredient.repository.IngredientRepository;
import org.dnd4.yorijori.domain.recipe.dto.RequestDto;
import org.dnd4.yorijori.domain.recipe.dto.ResponseDto;
import org.dnd4.yorijori.domain.recipe.repository.RecipeRepository;
import org.dnd4.yorijori.domain.recipe.service.RecipeListService;
import org.dnd4.yorijori.domain.recipe.service.RecipeService;
import org.dnd4.yorijori.domain.theme.entity.Theme;
import org.dnd4.yorijori.domain.theme.repository.ThemeRepository;
import org.dnd4.yorijori.domain.user.entity.User;
import org.dnd4.yorijori.domain.user.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
public class RecipeListTest {

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
    @Autowired
    RecipeListService recipeListService;

	static Long savedId;
	
    @Before
    public void 초기화() {
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

        savedId = recipeService.add(requestDto);
    }
    
	@Test
	public void 컷수_검색() throws Exception {
        List<ResponseDto> step1Search = recipeListService.findAll("1", null, null, null, null, null, 10, 0);
        assertEquals(step1Search.get(0).getId(), savedId);
        
	}
	@Test
	public void 시간_검색() throws Exception {
        List<ResponseDto> time15Search = recipeListService.findAll(null, "15", null, null, null, null, 10, 0);
        assertEquals(time15Search.get(0).getId(), savedId);
	}
	@Test
	public void 키워드_검색() throws Exception {
        List<ResponseDto> keywordTitleSearch = recipeListService.findAll(null, null, null, null, null, "title", 10, 0);
        assertEquals(keywordTitleSearch.get(0).getId(), savedId);
        List<ResponseDto> keywordIngredientSearch = recipeListService.findAll(null, null, null, null, null, "재료", 10, 0);
        assertEquals(keywordIngredientSearch.get(0).getId(), savedId);
        List<ResponseDto> keywordThemeSearch = recipeListService.findAll(null, null, null, null, null, "분위기", 10, 0);
        assertEquals(keywordThemeSearch.get(0).getId(), savedId);
	}
}
