package com.ga.food.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String time;

    @Column
    private Integer portions;

    @Column
    private String ingredients;

    @Column
    private String steps;

    @Column
    private boolean isPublic;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name= "category_id")
    private categories category;

    @Column
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @JsonIgnore // we put it to avoid enfent loop
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;



}
