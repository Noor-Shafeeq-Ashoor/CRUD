package com.ga.food.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name = "categories")
public class categories {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)// identity mean start from 1 ,2,3,4, --- as serial
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String imageURL;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // one category can contain more than one recipe // imp "EAGER = load the related data immediately."
    // If a child entity is removed from its parent relationship, delete that child from the database too.
    @OneToMany(fetch = FetchType.EAGER , mappedBy = "category", orphanRemoval = true)
    private List<Recipe> recipeList;








}
