package com.svetlicic.filip.trelloapp.trelloapp.services;

import com.svetlicic.filip.trelloapp.trelloapp.mapper.CardsMapper;
import com.svetlicic.filip.trelloapp.trelloapp.model.Board;
import com.svetlicic.filip.trelloapp.trelloapp.model.Cards;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.CardsDTO;
import com.svetlicic.filip.trelloapp.trelloapp.repositories.BoardRepository;
import com.svetlicic.filip.trelloapp.trelloapp.repositories.CardsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CardsServiceImpl implements CardsService{

    private final BoardRepository boardRepository;
    private final CardsRepository cardsRepository;
    private final CardsMapper cardsMapper;

    public CardsServiceImpl(BoardRepository boardRepository, CardsRepository cardsRepository, CardsMapper cardsMapper) {
        this.boardRepository = boardRepository;
        this.cardsRepository = cardsRepository;
        this.cardsMapper = cardsMapper;
    }

    @Override
    public CardsDTO saveCardsDTO(Long boardId, CardsDTO cardsDTO) {
        Optional<Board> boardOptional = boardRepository.findById(boardId);

        if(!boardOptional.isPresent()){
            //todo impl error handling
            log.error("board not found with id: " + boardId);
            return new CardsDTO();
        } else {
            Board board = boardOptional.get();

            String keyString = "";
            boolean quit = false;

            while(!quit){
                quit = true;
                keyString = GeneratedString.INSTANCE.generateRandomString();
                for(Cards cards : cardsRepository.findAll()){
                    if(cards.getKeyString().equals(keyString)){
                        quit = false;
                        break;
                    }
                }
            }

            Cards cardsFound = new Cards();

            if(cardsDTO.getId() != null){
                Optional<Cards> cardsOptional = board.getCardsSet()
                        .stream()
                        .filter(cards -> cards.getId().equals(cardsDTO.getId()))
                        .findFirst();
                if(cardsOptional.isPresent()){
                    cardsFound = cardsOptional.get();
                    cardsFound.setListName(cardsDTO.getListName());
                } else {
                    Cards cards = cardsMapper.cardsDtoToCards(cardsDTO);
                    cards.setId(null);
                    cards.setBoard(board);
                    cards.setKeyString(keyString);
                    board.getCardsSet().add(cards);
                }
            } else {
                Cards cards = cardsMapper.cardsDtoToCards(cardsDTO);
                cards.setBoard(board);
                cards.setKeyString(keyString);
                board.getCardsSet().add(cards);
            }

            Board savedBoard = boardRepository.save(board);

            String finalKeyString;

            if(cardsDTO.getId() == null){
                finalKeyString = keyString;
            } else {
                finalKeyString = cardsFound.getKeyString();
            }

            Optional<Cards> savedCardsOptional = savedBoard.getCardsSet().stream()
                    .filter(boardCards -> boardCards.getKeyString().equals(finalKeyString))
                    .findFirst();

            if(!savedCardsOptional.isPresent()){
                log.error("something went wrong!");
            }

            return cardsMapper.cardsToCardsDTO(savedCardsOptional.get());
        }
    }

    @Override
    public CardsDTO updateCardsDTO(Long boardId, Long cardsId, CardsDTO cardsDTO) {
        cardsDTO.setId(cardsId);
        return saveCardsDTO(boardId, cardsDTO);
    }

    @Override
    public void deleteCardsById(Long boardId, Long cardsId) {
        cardsRepository.deleteById(cardsId);
    }
}
