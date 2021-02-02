package org.dnd4.yorijori.domain.recipe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.dnd4.yorijori.domain.user.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name= "user_id", nullable=false)
    private User user_id;
	
	@ManyToOne
    @JoinColumn(name= "recipe_id", nullable=false)
    private Recipe recipe_id;
	
	private String content;
	private String imageUrl;
}
