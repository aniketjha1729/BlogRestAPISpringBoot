package com.rest.springRestApi.repository;

import com.rest.springRestApi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository  extends JpaRepository<Post, Long> {
}
