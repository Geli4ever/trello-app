package com.svetlicic.filip.trelloapp.trelloapp.services;

import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.BoardDTO;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.CardsDTO;

import java.util.List;

public interface BoardService {
    BoardDTO findByUserIdAndBoardId(Long userId, Long boardId);
    BoardDTO saveBoardDTO(Long id, BoardDTO boardDTO);
    BoardDTO updateBoardDTO(Long userId, Long boardId, BoardDTO boardDTO);
    void deleteById(Long userId, Long boardId);
    List<CardsDTO> getCardsSet(Long boardId);
}
