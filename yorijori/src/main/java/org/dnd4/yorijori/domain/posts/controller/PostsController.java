package org.dnd4.yorijori.domain.posts.controller;

import java.util.List;

import org.dnd4.yorijori.domain.posts.dto.PostsListResDto;
import org.dnd4.yorijori.domain.posts.dto.PostsResDto;
import org.dnd4.yorijori.domain.posts.service.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/posts")
public class PostsController {
	
	private final PostsService postsService;
	
	@GetMapping("/{id}")
	public PostsResDto findById(@PathVariable Long id) {
		return postsService.findById(id);
	}
	
	@GetMapping("/")
	public List<PostsListResDto> findAll(Model model) {
		return postsService.findAll();
	}
	
}
