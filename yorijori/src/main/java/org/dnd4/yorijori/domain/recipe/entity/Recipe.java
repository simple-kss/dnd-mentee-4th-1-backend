package org.dnd4.yorijori.domain.recipe.entity;

import javax.persistence.*;

import lombok.Builder;
import org.dnd4.yorijori.domain.comment.entity.Comment;
import org.dnd4.yorijori.domain.common.BaseTimeEntity;
import org.dnd4.yorijori.domain.common.YesOrNo;
import org.dnd4.yorijori.domain.ingredient.entity.Ingredient;
import org.dnd4.yorijori.domain.label.entity.Label;
import org.dnd4.yorijori.domain.rating.entity.Rating;
import org.dnd4.yorijori.domain.recipe_ingredient.entity.RecipeIngredient;
import org.dnd4.yorijori.domain.recipe_theme.entity.RecipeTheme;
import org.dnd4.yorijori.domain.step.entity.Step;
import org.dnd4.yorijori.domain.theme.entity.Theme;
import org.dnd4.yorijori.domain.user.entity.User;
import org.hibernate.annotations.ColumnDefault;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Entity
public class Recipe extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String title;

	private int step;
	private int time;

	@ColumnDefault("0")
	private int viewCount;
	private String thumbnail;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@OneToMany(mappedBy = "recipe")
	private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

	@OneToMany(mappedBy = "recipe")
	private List<Step> steps = new ArrayList<>();

	@OneToMany(mappedBy = "recipe")
	private List<Rating> ratings = new ArrayList<>();

	@OneToMany(mappedBy = "recipe")
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "recipe")
	private List<RecipeTheme> recipeThemes = new ArrayList<>();

	@OneToMany(mappedBy = "label")
	private List<Label> labels = new ArrayList<>();

	@Builder
	public Recipe(String title, int step, int time,String thumbnail, User user){
		this.title = title;
		this.step = step;
		this.time = time;
		this.thumbnail = thumbnail;
		this.user = user;
	}

	public List<Ingredient> getMainIngredients(){
		return this.getRecipeIngredients().stream()
				.filter(i->i.getIsSub().equals(YesOrNo.Y))
				.map(RecipeIngredient::getIngredient)
				.collect(Collectors.toList());
	}

	public List<Ingredient> getSubIngredients(){
		return this.getRecipeIngredients().stream()
				.filter(i->i.getIsSub().equals(YesOrNo.N))
				.map(RecipeIngredient::getIngredient)
				.collect(Collectors.toList());
	}

	public List<Theme> getThemes() {
		return this.getRecipeThemes().stream()
				.map(RecipeTheme::getTheme)
				.collect(Collectors.toList());
	}

	public double getAverageStarCount (){
		return this.getRatings().stream()
				.map(Rating::getStar)
				.reduce(0.0, Double::sum)
				/ this.getRatings().size();
	}

	// TODO: 2021-02-07 TODO count를 위해서 객체 다 불러오는건 비효율적이라는 생각이 들어, label 서비스에 count를 주는것을 만들어야 할듯
	public int getWishCount(){
		return this.getLabels().size();
	}
}
