package com.serviceflow.api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<Customer> customers;

    @Column(name ="name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email_id")
    private String email;

    @Column(name = "business_category")
    private String category;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

}
