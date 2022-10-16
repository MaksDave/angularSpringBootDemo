package com.maksdave.angularspringbootdemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maksdave.angularspringbootdemo.entity.enums.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, updatable = false)
    private String userName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(columnDefinition = "text")
    private String bio;
    @Column(length = 3000)
    private String password;
    
    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = "user_role",
    joinColumns = @JoinColumn(columnDefinition = "user_id"))
    private Set<Role> roles = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime dateCreated;
    
    @Transient
    private Collection<? extends GrantedAuthority> authorities;
    
    @PrePersist
    protected void onCreate(){
        this.dateCreated = LocalDateTime.now();
    }
    
}
