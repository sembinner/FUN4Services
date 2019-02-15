package com.fun4.services.repositories;

import com.fun4.services.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoy extends JpaRepository<User, Long> {
}
