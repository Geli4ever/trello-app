package com.svetlicic.filip.trelloapp.trelloapp.controllers;

import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.BoardDTO;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.BoardListDTO;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.UsersDTO;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UsersDTO getAllUsers(){
        return new UsersDTO(userService.getAllUsers());
    }

    @GetMapping({"{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("{userId}/boards")
    @ResponseStatus(HttpStatus.OK)
    public BoardListDTO getAllBoardsFromUser(@PathVariable Long userId){
        return new BoardListDTO(userService.getAllBoardsFromUser(userId));
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO newUser(@RequestBody UserDTO userDTO){
        return userService.saveOrUpdateUser(userDTO);
    }

    @PostMapping("{userId}/boards/new")
    @ResponseStatus(HttpStatus.CREATED)
    public BoardDTO newBoard(@PathVariable Long userId,@RequestBody BoardDTO boardDTO){
        BoardDTO savedBoard = boardService.saveBoardDTO(userId, boardDTO);
        savedBoard.setUserId(userId);
        return savedBoard;
    }

    @PutMapping("{userId}/boards/{boardId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BoardDTO updateBoard(@PathVariable Long userId, @RequestBody BoardDTO boardDTO, @PathVariable Long boardId){
        return boardService.updateBoardDTO(userId, boardId, boardDTO);
    }

    @DeleteMapping("{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUserById(userId);
    }

    @DeleteMapping("{userId}/boards/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBoard(@PathVariable Long userId, @PathVariable Long boardId){
        boardService.deleteById(userId, boardId);
    }
}
