package com.svetlicic.filip.trelloapp.trelloapp.services;

import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.CardDTO;

public interface CardService {
    CardDTO saveCardDTO(Long cardsId, CardDTO cardDTO);
}
