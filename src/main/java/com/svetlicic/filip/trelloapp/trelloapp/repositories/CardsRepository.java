package com.svetlicic.filip.trelloapp.trelloapp.repositories;

import com.svetlicic.filip.trelloapp.trelloapp.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardsRepository extends JpaRepository<Cards, Long> {
}
