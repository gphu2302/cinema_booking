package com.dev.cinema_booking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "users")
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name" , nullable = false)
    private String fullName;

    @Column(nullable = false , unique = true)
    private String email;

    @Column(name = "phone_number" , nullable = false , unique = true)
    private String phoneNumber;

    @Column(name = "hashed_password" , nullable = false)
    private String hashedPassword;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Column(name = "create_at" , nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }
}
