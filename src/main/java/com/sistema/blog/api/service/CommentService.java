package com.sistema.blog.api.service;

import com.sistema.blog.api.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    public CommentDTO createComment(long postId, CommentDTO commentDTO);
    public List<CommentDTO> getAllCommentByPostId(long postId);

    public CommentDTO getCommentById(Long postId, Long commentId);

    public CommentDTO updateComment(Long postId, Long commentId ,CommentDTO commentDTO);

    public void deleteComment(Long postId, Long commentId);
}
