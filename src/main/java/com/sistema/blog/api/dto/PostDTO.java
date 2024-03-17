package com.sistema.blog.api.dto;

import com.sistema.blog.api.entities.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long id;

    @NotEmpty
    @Size(min = 4, message = "The title of the publication must have must be 4 or more characters")
    private String title;

    @Size(min = 10, message = "The description of the publication must have must be 4 or more characters")
    private String description;

    @NotEmpty(message = "must not be empty")
    private String content;
    private Set<Comment> comments;

}
