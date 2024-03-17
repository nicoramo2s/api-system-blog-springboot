package com.sistema.blog.api.repository;

import com.sistema.blog.api.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {

    public Optional<Rol> findByName(String name);
}
