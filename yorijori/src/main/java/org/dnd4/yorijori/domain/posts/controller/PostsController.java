package org.dnd4.yorijori.domain.posts.controller;

import java.util.List;

import lombok.Data;
import org.dnd4.yorijori.domain.posts.dto.PostsListResDto;
import org.dnd4.yorijori.domain.posts.dto.PostsReqDto;
import org.dnd4.yorijori.domain.posts.dto.PostsResDto;
import org.dnd4.yorijori.domain.posts.service.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("/")
	public idResponse addPost(@RequestBody @Validated PostsReqDto reqDto){
		Long id = postsService.add(reqDto);
		return new idResponse(id);
	}
	@PutMapping("/{id}")
	public idResponse updatePost(@PathVariable Long id, @RequestBody @Validated PostsReqDto reqDto){
		postsService.update(id ,reqDto);
		return new idResponse(id);
	}

	@Data
	class idResponse{
		private Long id;

		public idResponse(Long id){
			this.id = id;
		}
	}
}
