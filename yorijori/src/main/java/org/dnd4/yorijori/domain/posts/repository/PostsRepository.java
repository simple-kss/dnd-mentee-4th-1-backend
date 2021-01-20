package org.dnd4.yorijori.domain.posts.repository;

import java.util.List;

import org.dnd4.yorijori.domain.posts.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostsRepository extends JpaRepository<Posts, Long> {
	
	@Query("SELECT p FROM Posts p WHERE p.title = ?1")
	List<Posts> findByTitle(String title);
	
}
