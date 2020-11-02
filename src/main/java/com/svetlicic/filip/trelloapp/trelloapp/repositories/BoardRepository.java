package com.svetlicic.filip.trelloapp.trelloapp.repositories;

import com.svetlicic.filip.trelloapp.trelloapp.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
