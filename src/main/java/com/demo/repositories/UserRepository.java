package com.demo.repositories;

import com.demo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
    Boolean existsByEmailAndPassword(String email, String password);
    Boolean existsByEmail(String email);
}
