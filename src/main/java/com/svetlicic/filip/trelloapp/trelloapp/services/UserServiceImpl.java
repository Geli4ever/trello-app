package com.svetlicic.filip.trelloapp.trelloapp.services;

import com.svetlicic.filip.trelloapp.trelloapp.mapper.BoardMapper;
import com.svetlicic.filip.trelloapp.trelloapp.mapper.UserMapper;
import com.svetlicic.filip.trelloapp.trelloapp.model.User;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.BoardDTO;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.UserDTO;
import com.svetlicic.filip.trelloapp.trelloapp.repositories.BoardRepository;
import com.svetlicic.filip.trelloapp.trelloapp.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final BoardMapper boardMapper;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public UserServiceImpl(UserMapper userMapper, BoardMapper boardMapper, UserRepository userRepository, BoardRepository boardRepository) {
        this.userMapper = userMapper;
        this.boardMapper = boardMapper;
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userMapper.userToUserDTO(userRepository.getOne(id));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO saveOrUpdateUser(UserDTO userDTO) {
        User detachedUser = userMapper.userDtoToUser(userDTO);
        return userMapper.userToUserDTO(userRepository.save(detachedUser));
    }

    @Override
    public void deleteUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isPresent()){
            User user = userOptional.get();
            userRepository.delete(user);
        } else {
            //todo impl error handling
            log.error("user not found with id: " + id);
        }
    }

    @Override
    public List<BoardDTO> getAllBoardsFromUser(Long id) {

        return userRepository.getOne(id).getBoards()
                .stream()
                .map(boardMapper::boardToBoardDTO)
                .peek(boardDTO -> boardDTO.setUserId(id))
                .collect(Collectors.toList());
    }

}
