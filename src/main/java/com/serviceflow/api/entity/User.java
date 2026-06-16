package com.serviceflow.api.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name ="name")
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email_id")
    private String email;
    @Column(name = "business_category")
    private String category;
    @Column(name = "created_at")
    private Date createdAt;

}
