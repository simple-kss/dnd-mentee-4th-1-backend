package org.dnd4.yorijori.domain.posts.dto;

import org.dnd4.yorijori.domain.posts.entity.Posts;

import lombok.Getter;

@Getter
public class PostsResDto {
	
	private Long id;
	private String title;
	
	public PostsResDto(Posts entity) {
		this.id=entity.getId();
		this.title=entity.getTitle();
	}

}
