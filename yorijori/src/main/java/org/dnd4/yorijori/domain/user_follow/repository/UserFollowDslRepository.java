package org.dnd4.yorijori.domain.user_follow.repository;

import static org.dnd4.yorijori.domain.recipe.entity.QRecipe.recipe;

import java.util.List;

import org.dnd4.yorijori.domain.recipe.entity.Recipe;
import static org.dnd4.yorijori.domain.user_follow.entity.QUserFollow.userFollow;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class UserFollowDslRepository extends QuerydslRepositorySupport {

	private final JPAQueryFactory queryFactory;

	public UserFollowDslRepository(JPAQueryFactory queryFactory) {
		super(Recipe.class);
		this.queryFactory = queryFactory;
	}

	public List<Recipe> followingFeed(Long userId, int limit, int offset) {
		List<Recipe> list = queryFactory
				.selectFrom(recipe)
				.where(recipe.user.in(
						JPAExpressions
						.select(userFollow.following)
						.from(userFollow)
						.where(userFollow.follower.id.eq(userId))))
				.orderBy(recipe.createdDate.desc())
				.limit(limit)
				.offset(offset)
				.fetch();
		return list;
	}
	
	public List<Recipe> followerFeed(Long userId, int limit, int offset) {
		List<Recipe> list = queryFactory
				.selectFrom(recipe)
				.where(recipe.user.in(
						JPAExpressions
						.select(userFollow.follower)
						.from(userFollow)
						.where(userFollow.following.id.eq(userId))))
				.orderBy(recipe.createdDate.desc())
				.limit(limit)
				.offset(offset)
				.fetch();
		return list;
	}
	
}
