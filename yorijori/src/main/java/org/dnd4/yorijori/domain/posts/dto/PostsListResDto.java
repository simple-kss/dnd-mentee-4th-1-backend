package org.dnd4.yorijori.domain.posts.dto;

import org.dnd4.yorijori.domain.posts.entity.Posts;

import lombok.Getter;

@Getter
public class PostsListResDto {
	
	private Long id;
	private String title;

	public PostsListResDto(Posts entity) {
		this.id=entity.getId();
		this.title=entity.getTitle();
	}

}
