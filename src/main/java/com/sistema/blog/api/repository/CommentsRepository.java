package com.sistema.blog.api.repository;

import com.sistema.blog.api.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByPostId(long postId);
}
