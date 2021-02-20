package org.dnd4.yorijori.domain.user_follow.repository;

import java.util.List;

import org.dnd4.yorijori.domain.user.entity.User;
import org.dnd4.yorijori.domain.user_follow.entity.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFollowRepository extends JpaRepository<UserFollow, Long> {
	List<User> findfollowingByfollower(User user);
	List<User> findfollewerByfollowing(User user);
}
