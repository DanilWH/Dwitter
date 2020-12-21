package com.example.Quoter.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Quoter.domain.User;

public interface UserRepo extends JpaRepository<User, Long>{
    
    User findByUsername(String username);
    User findByActivationCode(String code);

}
