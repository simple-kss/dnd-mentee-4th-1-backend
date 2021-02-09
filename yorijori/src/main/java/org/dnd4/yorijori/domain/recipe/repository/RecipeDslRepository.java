package org.dnd4.yorijori.domain.recipe.repository;

import java.util.List;

import org.dnd4.yorijori.domain.recipe.entity.QRecipe;
import org.dnd4.yorijori.domain.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class RecipeDslRepository extends QuerydslRepositorySupport{
	
	private final JPAQueryFactory queryFactory;
	
	public RecipeDslRepository(JPAQueryFactory queryFactory) {
        super(Recipe.class);
        this.queryFactory = queryFactory;
    }
	
	public List<Recipe> findById(Long id){
		QRecipe recipe = QRecipe.recipe;
		return queryFactory
				.selectFrom(recipe)
				.where(recipe.id.eq(id))
				.fetch();
	}
}
