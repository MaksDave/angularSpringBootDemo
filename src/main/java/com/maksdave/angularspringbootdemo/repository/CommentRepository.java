package com.maksdave.angularspringbootdemo.repository;

import com.maksdave.angularspringbootdemo.entity.Comment;
import com.maksdave.angularspringbootdemo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    List<Comment> findAllByPost(Post post);
    
    List<Comment> findByIdAndUserId(Long commentId, Long userId);
}
