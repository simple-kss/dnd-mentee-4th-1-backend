package org.dnd4.yorijori.domain.recipe.dto;

import lombok.Data;
import org.dnd4.yorijori.domain.ingredient.entity.Ingredient;
import org.dnd4.yorijori.domain.recipe.entity.Recipe;
import org.dnd4.yorijori.domain.step.entity.Step;
import org.dnd4.yorijori.domain.theme.entity.Theme;
import org.dnd4.yorijori.domain.user.entity.User;

import java.util.List;

@Data
public class UpdateRequestDto {
    private Long id;
    private String title;
    private String thumbnail;

    private List<Ingredient> mainIngredients;
    private List<Ingredient> subIngredients;
    private List<Theme> themes;
    private List<Step> steps;

    private int time;
    private double starCount;
    private int wishCount;
    private int viewCount;
    private User writer;


}
