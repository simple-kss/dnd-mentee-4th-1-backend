package org.dnd4.yorijori.domain.posts.controller;

import java.util.List;

import org.dnd4.yorijori.domain.posts.dto.PostsListResDto;
import org.dnd4.yorijori.domain.posts.dto.PostsResDto;
import org.dnd4.yorijori.domain.posts.service.PostsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostsController {
	
	private final PostsService postsService;
	
	@GetMapping("posts/{id}")
	public PostsResDto findById(@PathVariable Long id) {
		return postsService.findById(id);
	}
	
	@GetMapping("posts/title={title}")
	public List<PostsListResDto> findByTitle(@PathVariable String title) {
		return postsService.findByTitle(title);
	}
	
	@GetMapping("posts")
	public List<PostsListResDto> findAll() {
		return postsService.findAll();
	}
	
	@DeleteMapping("posts/{id}")
	public Long delete(@PathVariable Long id) {
		postsService.delete(id);
		return id;
	}
}
