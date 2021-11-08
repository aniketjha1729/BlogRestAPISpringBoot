package com.rest.springRestApi.controller;

import com.rest.springRestApi.dto.PostDto;
import com.rest.springRestApi.security.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostDto postDto) {
        postService.createPost(postDto);
        return new ResponseEntity(HttpStatus.OK);
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<PostDto>> showAllPosts() {
//        return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
//    }
//
//    @GetMapping("/get/{id}")
//    public ResponseEntity<PostDto> getSinglePost(@PathVariable @RequestBody Long id) {
//        return new ResponseEntity<>(postService.readSinglePost(id), HttpStatus.OK);
//    }
}
