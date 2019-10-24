package com.example.contentservice.controller;

import com.example.contentservice.domain.Post;
import com.example.contentservice.exception.PostAlreadyExistsException;
import com.example.contentservice.exception.PostNotFoundException;
import com.example.contentservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getPost(@PathVariable long id) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<Post>(postService.getPost(id), HttpStatus.OK);
        } catch (PostNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PostMapping("/post")
    public ResponseEntity<?> savePost(@RequestBody Post post) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<Post>(postService.savePost(post), HttpStatus.CREATED);
        } catch (PostAlreadyExistsException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PutMapping("/post")
    public ResponseEntity<?> updatePost(@RequestBody Post post) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<Post>(postService.updatePost(post), HttpStatus.OK);
        } catch (PostNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable long id) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<Post>(postService.deletePost(id), HttpStatus.OK);
        } catch (PostNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
