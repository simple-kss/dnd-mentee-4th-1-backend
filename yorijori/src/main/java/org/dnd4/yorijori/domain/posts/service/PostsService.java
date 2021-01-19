package org.dnd4.yorijori.domain.posts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.dnd4.yorijori.domain.posts.dto.PostsListResDto;
import org.dnd4.yorijori.domain.posts.dto.PostsReqDto;
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
	
	@Transactional(readOnly = true)
	public List<PostsListResDto> findAll(){
		return postsRepository.findAll()	
				.stream().map(posts -> new PostsListResDto(posts))
				.collect(Collectors.toList());
	}

	@Transactional
	public void update(PostsReqDto dto){

		Posts post = postsRepository.findById(dto.getId()).orElseThrow(
				() -> new IllegalArgumentException("해당 게시물이 없습니다. id = "+dto.getId()));

		post.setTitle(dto.getTitle());
		post.setSubTitle(dto.getSubTitle());
		post.setLikeCount(dto.getLikeCount());
		post.setImageUrl(dto.getImageUrl());
		post.setComment(dto.getComment());
		post.setCookingTime(dto.getCookingTime());
		post.setCookingTool(dto.getCookingTool());
	}

	@Transactional
	public void add(PostsReqDto dto){

		Posts post = Posts.createPost(dto.getTitle(), dto.getSubTitle(), dto.getLikeCount(), dto.getImageUrl(), dto.getComment(),dto.getCookingTime(), dto.getCookingTool());
		postsRepository.save(post);
	}
}
