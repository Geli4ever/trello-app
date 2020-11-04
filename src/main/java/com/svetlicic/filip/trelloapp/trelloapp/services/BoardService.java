package com.svetlicic.filip.trelloapp.trelloapp.services;

import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.BoardDTO;

public interface BoardService {
    BoardDTO findByUserIdAndBoardId(Long userId, Long boardId);
    BoardDTO saveBoardDTO(Long id, BoardDTO boardDTO);
}
