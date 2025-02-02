package com.demo.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID user_id;

    private String username;
    @Column(unique = true)
    private String email;
    private String password;

    private UserRoles roles;

    private LocalDateTime creation_date;
}
