package org.dnd4.yorijori.domain.posts.entity;

import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 500, nullable = false)
	private String title;
	
	@Column(length = 200, nullable = false)
	private String subTitle;
	
	private int likeCount;

	@ElementCollection
    @CollectionTable(name = "imageUrls", joinColumns = @JoinColumn(name = "pid"))
    private List<String> imageUrl;
	@ElementCollection
    @CollectionTable(name = "comments", joinColumns = @JoinColumn(name = "pid"))
    private List<String> comment;
	
	private String cookingTime;
	@Enumerated(EnumType.STRING)
	private CookingTool cookingTool;

	public static Posts createPost(String title, String subTitle, int likeCount, List<String> imageUrls, List<String> comments, String cookingTime, CookingTool cookingTool){
		Posts post = new Posts();
		post.setTitle(title);
		post.setSubTitle(subTitle);
		post.setLikeCount(likeCount);
		post.setImageUrl(imageUrls);
		post.setComment(comments);
		post.setCookingTime(cookingTime);
		post.setCookingTool(cookingTool);

		return post;
	}
	
}