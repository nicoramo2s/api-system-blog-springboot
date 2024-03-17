package com.sistema.blog.api.service;

import com.sistema.blog.api.dto.PostDTO;
import com.sistema.blog.api.dto.PostResponse;

public interface PostService {

    public PostDTO createPost(PostDTO postDTO);

    public PostResponse getAllPosts(int pageNumber, int pageSize);

    public PostDTO getPostbyId(long id);

    public PostDTO updatePost(PostDTO postDTO, long id);

    public void deletePostById(long id);
}
