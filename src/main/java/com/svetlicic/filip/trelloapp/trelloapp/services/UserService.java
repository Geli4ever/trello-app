package com.svetlicic.filip.trelloapp.trelloapp.services;

import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.BoardDTO;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.UserDTO;

import java.util.List;

public interface UserService{
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO saveOrUpdateUser(UserDTO userDTO);
    void deleteUser(UserDTO userDTO);
    void deleteUserById(Long id);
    List<BoardDTO> getAllBoardsFromUser(Long id);
}
