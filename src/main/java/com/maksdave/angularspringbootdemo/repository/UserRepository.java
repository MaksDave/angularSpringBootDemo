package com.maksdave.angularspringbootdemo.repository;

import com.maksdave.angularspringbootdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findUserByUserNameAnd(String username);
    
    Optional<User> findByEmailAnd(String email);
    
    Optional<User> findUserByIdAnd(Long id);
    
}
