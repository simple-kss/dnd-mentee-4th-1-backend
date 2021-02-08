package org.dnd4.yorijori.domain.user_follow.repository;

import org.dnd4.yorijori.domain.user_follow.entity.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFollowRepository extends JpaRepository<UserFollow, Long> {
}
