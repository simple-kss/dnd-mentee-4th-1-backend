package org.dnd4.yorijori.domain.posts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.dnd4.yorijori.domain.posts.dto.PostsListResDto;
import org.dnd4.yorijori.domain.posts.dto.PostsResDto;
import org.dnd4.yorijori.domain.posts.entity.CookingTool;
import org.dnd4.yorijori.domain.posts.entity.Posts;
import org.dnd4.yorijori.domain.posts.repository.PostRepository;
import org.dnd4.yorijori.domain.posts.repository.PostsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {
	private final PostsRepository postsRepository;
	private final PostRepository postsRepository_;

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
	public void update(Long id,String title, String subTitle, int likeCount, List<String> imageUrls, List<String> comments, String cookingTime, CookingTool cookingTool){

		Posts post = postsRepository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("해당 게시물이 없습니다. id = "+id));
		post.setTitle(title);
		post.setSubTitle(subTitle);
		post.setLikeCount(likeCount);
		post.setImageUrl(imageUrls);
		post.setComment(comments);
		post.setCookingTime(cookingTime);
		post.setCookingTool(cookingTool);
	}

	@Transactional
	public void add(String title, String subTitle, int likeCount, List<String> imageUrls, List<String> comments, String cookingTime, CookingTool cookingTool){
		Posts post = Posts.createPost(title, subTitle, likeCount, imageUrls, comments, cookingTime, cookingTool);
		postsRepository_.save(post);
	}
}
