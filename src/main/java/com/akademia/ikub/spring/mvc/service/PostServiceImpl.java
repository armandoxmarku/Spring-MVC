package com.akademia.ikub.spring.mvc.service;

import com.akademia.ikub.spring.mvc.model.Post;
import com.akademia.ikub.spring.mvc.model.User;

import java.util.HashMap;
import java.util.List;

public class PostServiceImpl implements PostService{

    private HashMap<Long,Post> posts = new HashMap<>();

    @Override
    public List<Post> getPostsByUserId(Long userId) {
        return null;
    }

    @Override
    public void createPost(Post post, Long userId) {

    }




}
