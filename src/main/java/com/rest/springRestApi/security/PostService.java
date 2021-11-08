package com.rest.springRestApi.security;

import com.rest.springRestApi.dto.PostDto;
import com.rest.springRestApi.model.Post;
import com.rest.springRestApi.repository.PostRepository;
import com.rest.springRestApi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PostService {

    @Autowired
    private AuthService authService;
    @Autowired
    private PostRepository postRepository;

    public void createPost(PostDto postDto) {
       Post post=new Post();
       post.setTitle(postDto.getTitle());
       post.setContent(postDto.getContent());
       User username=authService.getCurrentUser().orElseThrow(()->
               new IllegalArgumentException("No User logged in"));
       post.setUsername(username.getUsername());
       post.setCreatedOn(Instant.now());
       postRepository.save(post);
    }
}
