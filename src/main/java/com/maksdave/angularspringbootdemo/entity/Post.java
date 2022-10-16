package com.maksdave.angularspringbootdemo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String userLocation;
    private Integer likesAmount;
    
    @Column
    @ElementCollection(targetClass = String.class)
    private Set<String> usersWhoLiked = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "post", orphanRemoval = true)
    private List<Comment> listOfComments = new ArrayList<>();
    @Column(updatable = false)
    private LocalDateTime dateCreated;
    
    @PrePersist
    protected void onCreate(){
        this.dateCreated = LocalDateTime.now();
    }
}
