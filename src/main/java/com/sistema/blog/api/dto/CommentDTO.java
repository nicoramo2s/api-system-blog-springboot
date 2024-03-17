package com.sistema.blog.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private long id;

    @NotEmpty(message = "must not be empty")
    private String name;

    @NotEmpty(message = "must not be empty")
    @Email
    private String email;

    @NotEmpty(message = "must not be empty")
    @Size(min = 10, message = "The body of comment must have must be 10 or more characters")
    private String body;
}
