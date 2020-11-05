package com.svetlicic.filip.trelloapp.trelloapp.services;

import com.svetlicic.filip.trelloapp.trelloapp.mapper.BoardMapper;
import com.svetlicic.filip.trelloapp.trelloapp.mapper.CardMapper;
import com.svetlicic.filip.trelloapp.trelloapp.mapper.CardsMapper;
import com.svetlicic.filip.trelloapp.trelloapp.model.Board;
import com.svetlicic.filip.trelloapp.trelloapp.model.User;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.BoardDTO;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.CardsDTO;
import com.svetlicic.filip.trelloapp.trelloapp.repositories.BoardRepository;
import com.svetlicic.filip.trelloapp.trelloapp.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;
    private final CardsMapper cardsMapper;
    private final CardMapper cardMapper;

    public BoardServiceImpl(UserRepository userRepository, BoardRepository boardRepository, BoardMapper boardMapper, CardsMapper cardsMapper, CardMapper cardMapper) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
        this.cardsMapper = cardsMapper;
        this.cardMapper = cardMapper;
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
                keyString = GeneratedString.INSTANCE.generateRandomString();
                for(Board board : boardRepository.findAll()){
                    if(board.getKeyString().equals(keyString)){
                        quit = false;
                        break;
                    }
                }
            }

            Board boardFound = new Board();

            if(boardDTO.getId() != null){
                Optional<Board> boardOptional = user.getBoards()
                        .stream()
                        .filter(board -> board.getId().equals(boardDTO.getId()))
                        .findFirst();
                if(boardOptional.isPresent()){
                    boardFound = boardOptional.get();
                    boardFound.setBoardName(boardDTO.getBoardName());

                } else {
                    if(boardDTO.getBoardName() == null){
                        boardFound = boardRepository.getOne(boardDTO.getId());
                        boardFound.getUsers().add(user);
                        user.getBoards().add(boardFound);
                    } else {
                        Board board = boardMapper.boardDtoToBoard(boardDTO);
                        board.setId(null);
                        board.setKeyString(keyString);
                        board.getUsers().add(user);
                        user.addBoard(board);
                    }
                }
            } else {
                Board board = boardMapper.boardDtoToBoard(boardDTO);
                board.setKeyString(keyString);
                board.getUsers().add(user);
                user.addBoard(board);
            }

            User savedUser = userRepository.save(user);

            String finalKeyString;

            if(boardDTO.getId() == null){
                finalKeyString = keyString;
            } else {
                finalKeyString = boardFound.getKeyString();
            }
            Optional<Board> savedBoardOptional = savedUser.getBoards().stream()
                    .filter(userBoards -> userBoards.getKeyString().equals(finalKeyString))
                    .findFirst();

            if(!savedBoardOptional.isPresent()){
                log.error("something went wrong!");
            }

            return boardMapper.boardToBoardDTO(savedBoardOptional.get());
        }
    }

    @Override
    public BoardDTO updateBoardDTO(Long userId, Long boardId, BoardDTO boardDTO) {
        boardDTO.setId(boardId);
        return saveBoardDTO(userId, boardDTO);
    }

    @Override
    public void deleteById(Long userId, Long boardId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isPresent()){
            User user = userOptional.get();
            Optional<Board> boardOptional = user.getBoards()
                    .stream()
                    .filter(board -> board.getId().equals(boardId))
                    .findFirst();

            if(boardOptional.isPresent()){
                Board board = boardOptional.get();
                user.getBoards().remove(board);
                board.getUsers().remove(user);
                if(board.getUsers().size() > 0){
                    boardRepository.save(board);
                } else {
                    boardRepository.deleteById(boardId);
                }
            } else {
                //todo impl error handling
                log.error("board not found with id: " + boardId);
            }
        } else {
            //todo impl error handling
            log.error("user not found with id: " + userId);
        }


    }

    @Override
    public List<CardsDTO> getCardsSet(Long boardId) {

        return boardRepository.getOne(boardId).getCardsSet()
                .stream()
                .map(cardsMapper::cardsToCardsDTO)
                .collect(Collectors.toList());
    }

}
