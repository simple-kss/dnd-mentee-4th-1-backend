package org.dnd4.yorijori.service;

import org.dnd4.yorijori.domain.posts.dto.PostsReqDto;
import org.dnd4.yorijori.domain.posts.entity.CookingTool;
import org.dnd4.yorijori.domain.posts.entity.Posts;
import org.dnd4.yorijori.domain.posts.repository.PostsRepository;
import org.dnd4.yorijori.domain.posts.service.PostsService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class PostsServiceTest {
    @Autowired
    PostsService postsService;
    @Autowired
    PostsRepository postsRepository;


    @Test
    public void 게시물등록() throws Exception{
        PostsReqDto dto = new PostsReqDto();

        dto.setTitle("test title");
        dto.setSubTitle("test subtitle");

        List<String> comments= new ArrayList<>();
        comments.add("test comment");
        dto.setComment(comments);

        List<String> images = new ArrayList<>();
        images.add("test image");
        dto.setImageUrl(images);

        dto.setLikeCount(2);
        dto.setCookingTime("15분");
        dto.setCookingTool(CookingTool.airfryer);
        Long id = postsService.add(dto);

        Posts post = Posts.createPost(dto.getTitle(), dto.getSubTitle(), dto.getLikeCount(), dto.getImageUrl(), dto.getComment(), dto.getCookingTime(), dto.getCookingTool());

        Posts _post =  postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id = "+id));

        assertEquals(post.getTitle(), _post.getTitle());
        assertEquals(post.getSubTitle(), _post.getSubTitle());

        assertEquals(post.getLikeCount(), _post.getLikeCount());
        assertEquals(post.getCookingTool(), _post.getCookingTool());
        assertEquals(post.getCookingTime(), _post.getCookingTime());

    }
}
