package com.duoc.principedecolores.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    private String imageUri;

    @Column(nullable = false, updatable = false)
    private Long createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = System.currentTimeMillis();
    }
}
