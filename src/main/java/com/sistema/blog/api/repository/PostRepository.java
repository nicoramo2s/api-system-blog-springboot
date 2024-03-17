package com.sistema.blog.api.repository;

import com.sistema.blog.api.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
