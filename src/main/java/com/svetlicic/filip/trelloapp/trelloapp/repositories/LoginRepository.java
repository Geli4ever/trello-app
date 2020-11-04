package com.svetlicic.filip.trelloapp.trelloapp.repositories;

import com.svetlicic.filip.trelloapp.trelloapp.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
}
