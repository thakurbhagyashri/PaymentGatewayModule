package com.example.pgm.entities;

import jakarta.persistence.*;
import lombok.*;
import  java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String role;

    @Column(unique = true,nullable = false)
    private String password;

    private LocalDateTime CreatedAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist(){
        this.CreatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    public enum Role {
        ADMIN,USER
    }

}