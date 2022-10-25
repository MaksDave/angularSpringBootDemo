package com.maksdave.angularspringbootdemo.repository;

import com.maksdave.angularspringbootdemo.entity.Post;
import com.maksdave.angularspringbootdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    
    List<Post> findAllByUserOrderByDateCreatedDesc(User user);
    
    List<Post> findAllByOrderByDateCreatedDesc();
    
    Optional<Post> findPostByIdAndUser(Long id, User user);
}
