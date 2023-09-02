package com.hiufestainfo.domain.user.repository;


import com.hiufestainfo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByAuthInfoEmail(String email);
    Boolean existsByAuthInfoEmail(String email);
}
