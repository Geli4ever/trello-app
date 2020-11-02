package com.svetlicic.filip.trelloapp.trelloapp.repositories;

import com.svetlicic.filip.trelloapp.trelloapp.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
