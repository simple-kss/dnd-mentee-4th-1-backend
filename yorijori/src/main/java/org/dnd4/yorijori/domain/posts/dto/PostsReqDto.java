package org.dnd4.yorijori.domain.posts.dto;

import org.dnd4.yorijori.domain.posts.entity.CookingTool;
import org.dnd4.yorijori.domain.posts.entity.Posts;

import java.util.List;

public class PostsReqDto {
    private Long id;
    private String title;
    private String subTitle;
    private int likeCount;
    private List<String> imageUrl;
    private List<String> comment;
    private String cookingTime;
    private CookingTool cookingTool;

    public PostsReqDto(Posts entity) {
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.subTitle= entity.getSubTitle();
        this.likeCount= entity.getLikeCount();
        this.imageUrl= entity.getImageUrl();
        this.comment= entity.getComment();
        this.cookingTime= entity.getCookingTime();
        this.cookingTool= entity.getCookingTool();
    }
}
