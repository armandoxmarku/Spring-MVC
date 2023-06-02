package com.akademia.ikub.spring.mvc.service;

import com.akademia.ikub.spring.mvc.model.Post;
import com.akademia.ikub.spring.mvc.model.User;

import java.util.List;

public interface PostService {

    List<Post> getPostsByUserId(Long userId);
    void createPost(Post post, Long userId);
}
