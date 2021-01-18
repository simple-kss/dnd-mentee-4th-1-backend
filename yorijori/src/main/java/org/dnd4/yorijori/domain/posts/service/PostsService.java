package org.dnd4.yorijori.domain.posts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.dnd4.yorijori.domain.posts.dto.PostsListResDto;
import org.dnd4.yorijori.domain.posts.dto.PostsResDto;
import org.dnd4.yorijori.domain.posts.entity.Posts;
import org.dnd4.yorijori.domain.posts.repository.PostsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {
	private final PostsRepository postsRepository;
	
	public PostsResDto findById(Long id) {
		Posts entity = postsRepository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("해당 게시물이 없습니다. id = "+id));
		return new PostsResDto(entity);
	}
	
	public List<PostsListResDto> findByTitle(String title) {
		return postsRepository.findByTitle(title)	
				.stream().map(posts -> new PostsListResDto(posts))
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<PostsListResDto> findAll(){
		return postsRepository.findAll()	
				.stream().map(posts -> new PostsListResDto(posts))
				.collect(Collectors.toList());
	}
	@Transactional
	public void delete(Long id) {
		Posts posts = postsRepository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("해당 게시물이 없습니다. id = "+id));
		postsRepository.delete(posts);
	}
}
