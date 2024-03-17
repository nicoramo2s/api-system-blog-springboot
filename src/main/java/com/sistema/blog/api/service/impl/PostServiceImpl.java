package com.sistema.blog.api.service.impl;

import com.sistema.blog.api.dto.PostDTO;
import com.sistema.blog.api.dto.PostResponse;
import com.sistema.blog.api.entities.Post;
import com.sistema.blog.api.exceptions.ResourceNotFoundException;
import com.sistema.blog.api.repository.PostRepository;
import com.sistema.blog.api.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepository postRespository;

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        // CONVERTIMOS UN DTO A ENTIDAD
        Post post = mappingEntity(postDTO);

        Post newPost = postRespository.save(post);

        //CONVERTIMOS DE ENTIDAD A DTO

        return mappingDTO(newPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> posts = postRespository.findAll(pageable);

        List<Post> listPosts = posts.getContent();
        if (listPosts.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "don't exist posts");
        List<PostDTO> content = listPosts.stream().map(this::mappingDTO).toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElement(posts.getTotalElements());
        postResponse.setTotalPage(posts.getTotalPages());
        postResponse.setUltimate(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDTO getPostbyId(long id) {
        Post post = postRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        return mappingDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, long id) {
        Post post = postRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        // Actualizar solo los campos que se proporcionan en el DTO
        if (postDTO.getTitle() != null)
            post.setTitle(postDTO.getTitle());

        if (postDTO.getDescription() != null)
            post.setDescription(postDTO.getDescription());

        if (postDTO.getContent() != null)
            post.setContent(postDTO.getContent());


        Post updatePost = postRespository.save(post);
        return mappingDTO(updatePost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        postRespository.delete(post);
    }

    // CONVIERTE ENTIDAD A DTO
    private PostDTO mappingDTO(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }

    //CONVIERTE DE DTO A ENTIDAD
    private Post mappingEntity(PostDTO postDTO) {
        return modelMapper.map(postDTO, Post.class);
    }
}
