package org.dnd4.yorijori.domain.user_follow.service;

import java.util.List;
import java.util.stream.Collectors;

import org.dnd4.yorijori.domain.recipe.dto.UserDto;
import org.dnd4.yorijori.domain.user.entity.User;
import org.dnd4.yorijori.domain.user_follow.entity.UserFollow;
import org.dnd4.yorijori.domain.user_follow.repository.UserFollowRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserFollowService {
	
	private final UserFollowRepository userFollowRepository;

	public List<UserDto> followingList(User user) {
		List<UserDto> result = userFollowRepository.findfollowingByfollower(user).stream()
				.map(UserDto::new).collect(Collectors.toList());
		return result;
	}
	
	public List<UserDto> followerList(User user) {
		return userFollowRepository.findfollewerByfollowing(user).stream()
				.map(UserDto::new).collect(Collectors.toList());
	}
	
	@Transactional
    public void follow(User following, User follower){
		UserFollow entity = UserFollow.builder().follower(follower).following(following).build();
		userFollowRepository.save(entity);
    }
	
	@Transactional
    public void unfollow(User following, User follower){
		UserFollow entity = userFollowRepository.findByFollowerAndFollowing(follower, following).orElseThrow(()->new IllegalArgumentException("데이터가 없습니다."));
		userFollowRepository.delete(entity);
    }
	
}
