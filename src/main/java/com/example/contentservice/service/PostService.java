package com.example.contentservice.service;

import com.example.contentservice.domain.Post;
import com.example.contentservice.exception.PostAlreadyExistsException;
import com.example.contentservice.exception.PostNotFoundException;

public interface PostService {
    Post getPost(long id) throws PostNotFoundException;
    Post savePost(Post post) throws PostAlreadyExistsException;
    Post updatePost(Post post) throws PostNotFoundException;
    Post deletePost(long id) throws PostNotFoundException;
}
