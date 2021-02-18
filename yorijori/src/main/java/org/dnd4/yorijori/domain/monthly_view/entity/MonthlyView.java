package org.dnd4.yorijori.domain.monthly_view.entity;

import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RedisHash("monthlyViews")
public class MonthlyView {

	@Id
	private Long id;
	@Indexed
	private Long recipeId;
	private int count;
	
	public void increase() {
		this.count++;
	}
}
