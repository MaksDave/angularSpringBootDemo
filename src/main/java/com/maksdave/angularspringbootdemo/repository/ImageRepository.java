package com.maksdave.angularspringbootdemo.repository;

import com.maksdave.angularspringbootdemo.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    Optional<ImageModel> findByUserIdAnd(Long userId);
    
    Optional<ImageModel> findByPostIdAnd(Long postId);
}
