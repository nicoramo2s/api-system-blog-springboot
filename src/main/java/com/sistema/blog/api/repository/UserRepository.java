package com.sistema.blog.api.repository;

import com.sistema.blog.api.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    public Optional<Users> findByEmail(String email);
    public Optional<Users> findByUsernameOrEmail(String username, String email);
    public Optional<Users> findByUsername(String username);
    public Boolean existsByUsername(String username);
    public Boolean existsByEmail(String email);
}
