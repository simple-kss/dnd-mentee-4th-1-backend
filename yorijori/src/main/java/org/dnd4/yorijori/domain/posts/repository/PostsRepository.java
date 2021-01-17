package org.dnd4.yorijori.domain.posts.repository;

import org.dnd4.yorijori.domain.posts.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {

}
