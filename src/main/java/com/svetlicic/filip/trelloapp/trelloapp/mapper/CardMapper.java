package com.svetlicic.filip.trelloapp.trelloapp.mapper;

import com.svetlicic.filip.trelloapp.trelloapp.model.Card;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.CardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    Card cardDtoToCard(CardDTO cardDTO);
    CardDTO cardToCardDTO(Card card);
}
