package org.dnd4.yorijori.domain.recipe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.dnd4.yorijori.domain.posts.entity.BaseTimeEntity;
import org.dnd4.yorijori.domain.user.entity.User;
import org.hibernate.annotations.ColumnDefault;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Recipe extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String title;

	private int step;
	private int time;

	@ColumnDefault("0")
	private int viewCount;
	private String thumnail;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

}
