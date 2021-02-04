package org.dnd4.yorijori.domain.posts.controller;

import java.util.List;

import org.dnd4.yorijori.domain.posts.dto.PostsListResDto;
import org.dnd4.yorijori.domain.posts.dto.PostsReqDto;
import org.dnd4.yorijori.domain.posts.dto.PostsResDto;
import org.dnd4.yorijori.domain.posts.service.PostsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostsController {
	
	private final PostsService postsService;
	
	@GetMapping("/{id}")
	public PostsResDto findById(@PathVariable Long id) {
		return postsService.findById(id);
	}
	
	@GetMapping("/title={title}")
	public List<PostsListResDto> findByTitle(@PathVariable String title) {
		return postsService.findByTitle(title);
	}
	
	@GetMapping("")
	public List<PostsListResDto> findAll() {
		return postsService.findAll();
	}


	@PostMapping("")
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
	class idResponse {
		private Long id;

		public idResponse(Long id) {
			this.id = id;
		}
	}
	
	@DeleteMapping("posts/{id}")
	public Long delete(@PathVariable Long id) {
		postsService.delete(id);
		return id;
	}
}
