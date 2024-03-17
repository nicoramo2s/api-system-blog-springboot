package com.sistema.blog.api.service.impl;

import com.sistema.blog.api.dto.CommentDTO;
import com.sistema.blog.api.entities.Comment;
import com.sistema.blog.api.entities.Post;
import com.sistema.blog.api.exceptions.BlogAppException;
import com.sistema.blog.api.exceptions.ResourceNotFoundException;
import com.sistema.blog.api.repository.CommentsRepository;
import com.sistema.blog.api.repository.PostRepository;
import com.sistema.blog.api.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentsRepository commentRespository;
    @Autowired
    private PostRepository postRespository;

    @Override
    public CommentDTO createComment(long postId, CommentDTO commentDTO) {
        Comment comment = mappedToEntity(commentDTO);
        Post post = postRespository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
        comment.setPost(post);
        Comment newComment = commentRespository.save(comment);
        return mappedToDTO(newComment);
    }

    @Override
    public List<CommentDTO> getAllCommentByPostId(long postId) {
        List<Comment> comments = commentRespository.findByPostId(postId);
        return comments.stream().map(comment -> mappedToDTO(comment)).toList();
    }

    @Override
    public CommentDTO getCommentById(Long postId, Long commentId) {
        // Exception not found, no exist post
        Post post = postRespository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
        // Exception not found, no exist comment
        Comment comment = commentRespository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));

        if (!comment.getPost().getId().equals(post.getId()))
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "the comment does not belong to the post");

        return mappedToDTO(comment);
    }

    @Override
    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO) {
        // Exception not found, no exist post
        Post post = postRespository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
        // Exception not found, no exist comment
        Comment comment = commentRespository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));

        if (!comment.getPost().getId().equals(post.getId()))
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "the comment does not belong to the post");

        if (commentDTO.getName() != null)
            comment.setName(commentDTO.getName());

        if (commentDTO.getEmail() != null)
            comment.setEmail(commentDTO.getEmail());

        if (commentDTO.getBody() != null)
            comment.setBody(commentDTO.getBody());

        Comment updateComment = commentRespository.save(comment);

        return mappedToDTO(updateComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        // Exception not found, no exist post
        Post post = postRespository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
        // Exception not found, no exist comment
        Comment comment = commentRespository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));

        if (!comment.getPost().getId().equals(post.getId()))
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "the comment does not belong to the post");

        commentRespository.delete(comment);
    }

    private CommentDTO mappedToDTO(Comment comment) {
        return modelMapper.map(comment, CommentDTO.class);
    }

    private Comment mappedToEntity(CommentDTO commentDTO) {
        return modelMapper.map(commentDTO, Comment.class);
    }
}
