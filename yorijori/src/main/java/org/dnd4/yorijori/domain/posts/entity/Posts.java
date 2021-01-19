package org.dnd4.yorijori.domain.posts.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 500, nullable = false)
	private String title;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String subTitle;
	
	private int likeCount;

	@ElementCollection
    @CollectionTable(name = "imageUrls", joinColumns = @JoinColumn(name = "pid"))
    private List<String> imageUrl;
	@ElementCollection
    @CollectionTable(name = "comments", joinColumns = @JoinColumn(name = "pid"))
    private List<String> comment;
	
	private String cookingTime;
	private CookingTool cookingTool;
	
}