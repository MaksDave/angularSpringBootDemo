package com.maksdave.angularspringbootdemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maksdave.angularspringbootdemo.entity.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
public class User implements UserDetails {
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
    
    public User(Long id, String userName, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    
    /**
     * Security methods
     * @return
     */
    
    @Override
    public String getPassword(){
        return password;
    }
    
    @Override
    public String getUsername() {
        return userName;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
}
