package com.svetlicic.filip.trelloapp.trelloapp.services;

import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.CardDTO;

public interface CardService {
    CardDTO saveCardDTO(Long cardsId, CardDTO cardDTO);
    CardDTO updateCardDTO(Long cardsId, Long cardId, CardDTO cardDTO);
    void deleteCardById(Long boardId, Long cardsId, Long cardId);
}
