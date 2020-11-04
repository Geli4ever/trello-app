package com.svetlicic.filip.trelloapp.trelloapp.mapper;

import com.svetlicic.filip.trelloapp.trelloapp.model.Cards;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.CardsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardsMapper {

    CardsMapper INSTANCE = Mappers.getMapper(CardsMapper.class);

    @Mapping(target = "cards", ignore = true)
    @Mapping(target = "board", ignore = true)
    Cards cardsDtoToCards(CardsDTO cardsDTO);


    CardsDTO cardsToCardsDTO(Cards cards);
}
