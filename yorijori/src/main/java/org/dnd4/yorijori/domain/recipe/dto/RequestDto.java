package org.dnd4.yorijori.domain.recipe.dto;

import lombok.Data;

import javax.persistence.Embeddable;
import java.util.List;

@Data
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
    public class Step {
        private String image;
        private String description;
    }


}

