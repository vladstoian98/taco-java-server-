package com.example.tacocloudapplication.repo.impl;

import com.example.tacocloudapplication.table.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
