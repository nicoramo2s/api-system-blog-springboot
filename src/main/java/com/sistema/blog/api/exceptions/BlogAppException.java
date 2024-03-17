package com.sistema.blog.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogAppException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private HttpStatus status;

    private String message;

    public BlogAppException(HttpStatus status, String message, String message1) {
        this.status = status;
        this.message = message;
        this.message = message1;
    }
}
