package com.svetlicic.filip.trelloapp.trelloapp.services;

import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.CardsDTO;

public interface CardsService {

    CardsDTO saveCardsDTO(Long boardId, CardsDTO cardsDTO);
}
