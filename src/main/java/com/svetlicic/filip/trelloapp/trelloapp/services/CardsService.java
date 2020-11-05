package com.svetlicic.filip.trelloapp.trelloapp.services;

import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.CardDTO;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.CardsDTO;

import java.util.List;

public interface CardsService {

    CardsDTO saveCardsDTO(Long boardId, CardsDTO cardsDTO);
    CardsDTO updateCardsDTO(Long boardId, Long cardsId, CardsDTO cardsDTO);
    void deleteCardsById(Long boardId, Long cardsId);
    List<CardDTO> getCards(Long cardsId);
}
