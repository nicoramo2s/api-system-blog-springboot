package com.sistema.blog.api.controller;

import com.sistema.blog.api.dto.CommentDTO;
import com.sistema.blog.api.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDTO> createNewComment(@PathVariable long postId,@Valid @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long postId, @PathVariable Long id) {
        CommentDTO commentDTO = commentService.getCommentById(postId, id);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}/comments")
    public List<CommentDTO> getCommentsByPostId(@PathVariable Long postId) {
        return commentService.getAllCommentByPostId(postId);
    }

    @PutMapping("/post/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long postId, @PathVariable Long id, @Valid @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.updateComment(postId, id, commentDTO), HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long id) {
        commentService.deleteComment(postId, id);
        return new ResponseEntity<>("comment removed successfully", HttpStatus.OK);
    }
}