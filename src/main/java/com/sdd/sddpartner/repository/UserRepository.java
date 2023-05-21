package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
