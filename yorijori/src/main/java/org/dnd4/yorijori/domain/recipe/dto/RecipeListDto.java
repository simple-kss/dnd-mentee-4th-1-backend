package org.dnd4.yorijori.domain.recipe.dto;

import org.dnd4.yorijori.domain.recipe.entity.Recipe;
import org.dnd4.yorijori.domain.user.entity.User;

import lombok.Getter;

@Getter
public class RecipeListDto {
	private Long id;
	private String title;
	private int step;
	private int time;
	private int viewCount;
	private String thumnail;
	private User user;

	public RecipeListDto(Recipe recipe) {
		this.id = recipe.getId();
		this.title = recipe.getTitle();
		this.step = recipe.getStep();
		this.time = recipe.getTime();
		this.viewCount = recipe.getViewCount();
		this.thumnail = recipe.getThumnail();
		this.user = recipe.getUser();
	}
}
