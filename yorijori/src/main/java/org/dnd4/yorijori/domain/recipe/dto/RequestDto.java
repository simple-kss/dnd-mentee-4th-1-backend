package org.dnd4.yorijori.domain.recipe.dto;

import lombok.Data;
import org.dnd4.yorijori.domain.user.entity.User;

import java.util.List;

@Data
public class RequestDto {
    private Long recipeId;
    private String title;
    private String thumbnail;

    private List<Long> mainIngredientIds;
    private List<Long> subIngredientIds;
    private List<Long> themeIds;
    private List<Long> stepIds;

    private int time;
    private Long writerId;
}
