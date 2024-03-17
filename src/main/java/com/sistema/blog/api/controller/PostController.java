package com.sistema.blog.api.controller;

import com.sistema.blog.api.dto.PostDTO;
import com.sistema.blog.api.dto.PostResponse;
import com.sistema.blog.api.service.PostService;
import com.sistema.blog.api.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {
        PostDTO data = postService.createPost(postDTO);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.NUMBER_PAGE_DEFAULT, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.SIZE_PAGE_DEFAULT, required = false) int pageSize
    ) {
        return postService.getAllPosts(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable long id) {
        return ResponseEntity.ok(postService.getPostbyId(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePostById(@PathVariable long id, @Valid @RequestBody PostDTO postDTO) {
        PostDTO data = postService.updatePost(postDTO, id);
        System.out.println(data);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

}
