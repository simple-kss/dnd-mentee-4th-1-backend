package org.dnd4.yorijori.domain.comment.repository;

import org.dnd4.yorijori.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
