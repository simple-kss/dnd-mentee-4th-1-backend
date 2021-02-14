package org.dnd4.yorijori.domain.recipe.dto;

import java.util.List;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestDto {

    private String title;
    private String thumbnail;

    private List<Long> mainIngredientIds;
    private List<Long> subIngredientIds;
    private List<Long> themeIds;
    private List<Step> steps;

    private int time;
    private Long writerId;

    @Embeddable
    @Data
    static public class Step {
        private String image;
        private String description;
    }


}

