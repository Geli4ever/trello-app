package com.svetlicic.filip.trelloapp.trelloapp.controllers;

import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.BoardDTO;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.BoardListDTO;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.UserDTO;
import com.svetlicic.filip.trelloapp.trelloapp.services.BoardService;
import com.svetlicic.filip.trelloapp.trelloapp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/")
public class UserController {

    private final UserService userService;
    private final BoardService boardService;

    public UserController(UserService userService, BoardService boardService) {
        this.userService = userService;
        this.boardService = boardService;
    }

    @GetMapping({"{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getCustomerById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("{userId}/boards")
    @ResponseStatus(HttpStatus.OK)
    public BoardListDTO getAllBoardsFromUser(@PathVariable Long userId){
        return new BoardListDTO(userService.getAllBoardsFromUser(userId));
    }

    @PostMapping("{userId}/boards")
    @ResponseStatus(HttpStatus.CREATED)
    public BoardDTO newBoard(@PathVariable Long userId,@RequestBody BoardDTO boardDTO){
        BoardDTO savedBoard = boardService.saveBoardDTO(userId, boardDTO);
        savedBoard.setUserId(userId);
        return savedBoard;
    }

}
