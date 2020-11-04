package com.svetlicic.filip.trelloapp.trelloapp.services;

import com.svetlicic.filip.trelloapp.trelloapp.mapper.BoardMapper;
import com.svetlicic.filip.trelloapp.trelloapp.model.Board;
import com.svetlicic.filip.trelloapp.trelloapp.model.User;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.BoardDTO;
import com.svetlicic.filip.trelloapp.trelloapp.repositories.BoardRepository;
import com.svetlicic.filip.trelloapp.trelloapp.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    public BoardServiceImpl(UserRepository userRepository, BoardRepository boardRepository, BoardMapper boardMapper) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
    }

    @Override
    public BoardDTO findByUserIdAndBoardId(Long userId, Long boardId) {

        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isPresent()){
            //todo impl error handling
            log.error("user not found with id: " + userId);
        }

        User user = optionalUser.get();

        Optional<BoardDTO> boardDTOOptional = user.getBoards().stream()
                .filter(board -> board.getId().equals(boardId))
                .map(boardMapper::boardToBoardDTO).findFirst();

        if(!boardDTOOptional.isPresent()){
            //todo impl error handling
            log.error("board not found with id: " + boardId);
        }
        return boardDTOOptional.get();
    }

    @Override
    @Transactional
    public BoardDTO saveBoardDTO(Long id, BoardDTO boardDTO) {
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()){
            //todo impl error handling
            log.error("user not found with id: " + id);
            return new BoardDTO();
        } else {
            User user = userOptional.get();
            String keyString = "";
            boolean quit = false;

            while(!quit){
                quit = true;
                keyString = generateRandomString();
                for(Board board : boardRepository.findAll()){
                    if(board.getKeyString().equals(keyString)){
                        quit = false;
                        break;
                    }
                }
            }

            if(boardDTO.getId() != null){
                Optional<Board> boardOptional = user.getBoards()
                        .stream()
                        .filter(board -> board.getId().equals(boardDTO.getId()))
                        .findFirst();
                if(boardOptional.isPresent()){
                    Board boardFound = boardOptional.get();
                    boardFound.setBoardName(boardDTO.getBoardName());
                } else {
                    Board board = boardMapper.boardDtoToBoard(boardDTO);
                    board.setId(null);
                    board.setKeyString(keyString);
                    board.getUsers().add(user);
                    user.addBoard(board);
                }
            } else {
                Board board = boardMapper.boardDtoToBoard(boardDTO);
                board.setKeyString(keyString);
                board.getUsers().add(user);
                user.addBoard(board);
            }

            User savedUser = userRepository.save(user);

            String finalKeyString = keyString;
            Optional<Board> savedBoardOptional = savedUser.getBoards().stream()
                    .filter(userBoards -> userBoards.getKeyString().equals(finalKeyString))
                    .findFirst();

            if(!savedBoardOptional.isPresent()){
                log.error("something went wrong!");
            }

            return boardMapper.boardToBoardDTO(savedBoardOptional.get());
        }
    }

    private String generateRandomString(){
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
