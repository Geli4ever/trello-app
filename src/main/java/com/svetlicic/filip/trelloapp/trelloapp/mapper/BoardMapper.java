package com.svetlicic.filip.trelloapp.trelloapp.mapper;

import com.svetlicic.filip.trelloapp.trelloapp.model.Board;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.BoardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BoardMapper {

    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    @Mapping(target = "cardsSet", ignore = true)
    @Mapping(target = "users", ignore = true)
    Board boardDtoToBoard(BoardDTO boardDTO);


    BoardDTO boardToBoardDTO(Board board);


}
