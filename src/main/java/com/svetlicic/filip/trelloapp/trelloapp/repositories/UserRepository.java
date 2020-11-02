package com.svetlicic.filip.trelloapp.trelloapp.repositories;

import com.svetlicic.filip.trelloapp.trelloapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
