package com.sistema.blog.api.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtAuthResponseDTO {
    private String tokenAccess;
    private String typeToken = "Bearer";

    public JwtAuthResponseDTO(String tokenAccess) {
        this.tokenAccess = tokenAccess;
    }
}
