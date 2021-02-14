package org.dnd4.yorijori.domain.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class UpdateRequestDto {
    private String title;
    private String thumbnail;

    private List<Long> mainIngredientIds;
    private List<Long> subIngredientIds;
    private List<Long> themeIds;
    private List<StepDto> steps;

    private int time;
    private int viewCount;

}


