package org.dnd4.yorijori.domain.posts.dto;

import java.util.List;

import org.dnd4.yorijori.domain.posts.entity.CookingTool;
import org.dnd4.yorijori.domain.posts.entity.Posts;

import lombok.Getter;

@Getter
public class PostsResDto {
	
	private Long id;
	private String title;
	private String subTitle;
	private int likeCount;
	private String cookingTime;
	private CookingTool cookingTool;
	private List<String> imageUrl;
	private List<String> comment;

	public PostsResDto(Posts entity) {
		this.id=entity.getId();
		this.title=entity.getTitle();
		this.subTitle=entity.getSubTitle();
		this.likeCount=entity.getLikeCount();
		this.cookingTime=entity.getCookingTime();
		this.cookingTool=entity.getCookingTool();
		this.imageUrl=entity.getImageUrl();
		this.comment=entity.getComment();
	}

}
