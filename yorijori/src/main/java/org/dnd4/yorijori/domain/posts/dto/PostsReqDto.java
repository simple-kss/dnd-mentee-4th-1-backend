package org.dnd4.yorijori.domain.posts.dto;

import lombok.Getter;
import lombok.Setter;
import org.dnd4.yorijori.domain.posts.entity.CookingTool;
import org.dnd4.yorijori.domain.posts.entity.Posts;

import java.util.List;

@Getter @Setter
public class PostsReqDto {
    private Long id;
    private String title;
    private String subTitle;
    private int likeCount;
    private List<String> imageUrl;
    private List<String> comment;
    private String cookingTime;
    private CookingTool cookingTool;
}
