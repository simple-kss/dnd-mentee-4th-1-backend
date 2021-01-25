package org.dnd4.yorijori.domain.posts;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import org.dnd4.yorijori.domain.posts.dto.PostsListResDto;
import org.dnd4.yorijori.domain.posts.dto.PostsResDto;
import org.dnd4.yorijori.domain.posts.entity.CookingTool;
import org.dnd4.yorijori.domain.posts.entity.Posts;
import org.dnd4.yorijori.domain.posts.repository.PostsRepository;
import org.dnd4.yorijori.domain.posts.service.PostsService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PostServiceTest {
	
	@Autowired
	private PostsRepository postsRepository;
	
	@Autowired
	private PostsService postsService;
	
	@After
	public void cleanup() {
		postsRepository.deleteAll();
	}
	
	@Test
	public void findAll() {
		List<String> list = Collections.emptyList();
		postsRepository.save(
				Posts.createPost("title1", "subTitle1", 1, list, list, "", CookingTool.airfryer)
				);
		postsRepository.save(
				Posts.createPost("title2", "subTitle2", 1, list, list, "", CookingTool.airfryer)
				);
		
		List<PostsListResDto> postsList = postsService.findAll();
		
		assertThat(postsList.get(0).getTitle()).isEqualTo("title1");
		assertThat(postsList.get(0).getSubTitle()).isEqualTo("subTitle1");
		assertThat(postsList.get(1).getTitle()).isEqualTo("title2");
		assertThat(postsList.get(1).getSubTitle()).isEqualTo("subTitle2");
	}
	@Test
	public void findById() {
		List<String> list = Collections.emptyList();
		postsRepository.save(
				Posts.createPost("title1", "subTitle1", 1, list, list, "", CookingTool.airfryer)
				);
		postsRepository.save(
				Posts.createPost("title2", "subTitle2", 1, list, list, "", CookingTool.airfryer)
				);
		
		PostsResDto posts = postsService.findById(1L);
		
		assertThat(posts.getTitle()).isEqualTo("title1");
		assertThat(posts.getSubTitle()).isEqualTo("subTitle1");
	}
	@Test
	public void findByTitle() {
		List<String> list = Collections.emptyList();
		postsRepository.save(
				Posts.createPost("title1", "subTitle1", 1, list, list, "", CookingTool.airfryer)
				);
		postsRepository.save(
				Posts.createPost("title1", "subTitle2", 1, list, list, "", CookingTool.airfryer)
				);
		postsRepository.save(
				Posts.createPost("title2", "subTitle2", 1, list, list, "", CookingTool.airfryer)
				);
		
		List<PostsListResDto> postsList = postsService.findByTitle("title1");
		assertThat(postsList.size()).isEqualTo(2);
		assertThat(postsList.get(0).getTitle()).isEqualTo("title1");
		assertThat(postsList.get(1).getTitle()).isEqualTo("title1");
	}
	@Test
	public void delete() {
		List<String> list = Collections.emptyList();
		postsRepository.save(
				Posts.createPost("title1", "subTitle1", 1, list, list, "", CookingTool.airfryer)
				);
		postsRepository.save(
				Posts.createPost("title2", "subTitle2", 1, list, list, "", CookingTool.airfryer)
				);
		
		postsService.delete(1L);
		postsService.delete(2L);
		List<PostsListResDto> postsList = postsService.findAll();
		assertThat(postsList.size()).isEqualTo(0);
	}
}
