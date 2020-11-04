package com.svetlicic.filip.trelloapp.trelloapp.controllers;

import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.CardDTO;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.CardsDTO;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.CardsListDTO;
import com.svetlicic.filip.trelloapp.trelloapp.services.BoardService;
import com.svetlicic.filip.trelloapp.trelloapp.services.CardService;
import com.svetlicic.filip.trelloapp.trelloapp.services.CardsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/b/")
public class BoardController {

    private final BoardService boardService;
    private final CardsService cardsService;
    private final CardService cardService;

    public BoardController(BoardService boardService, CardsService cardsService, CardService cardService) {
        this.boardService = boardService;
        this.cardsService = cardsService;
        this.cardService = cardService;
    }

    @GetMapping("{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public CardsListDTO getAllCardsSets(@PathVariable Long boardId){
        return new CardsListDTO(boardService.getCardsSet(boardId));
    }

    @PostMapping("{boardId}/cards")
    @ResponseStatus(HttpStatus.CREATED)
    public CardsDTO newCardSet(@PathVariable Long boardId, @RequestBody CardsDTO cardsDTO){
        CardsDTO savedCardsDTO = cardsService.saveCardsDTO(boardId, cardsDTO);
        savedCardsDTO.setBoardId(boardId);
        return savedCardsDTO;
    }

    @PostMapping("{boardId}/cards/{cardsId}/card")
    @ResponseStatus(HttpStatus.CREATED)
    public CardDTO newCard(@PathVariable Long cardsId, @RequestBody CardDTO cardDTO, @PathVariable Long boardId){
        CardDTO savedCardDTO = cardService.saveCardDTO(cardsId, cardDTO);
        savedCardDTO.setBoardId(boardId);
        savedCardDTO.setCardsId(cardsId);
        return savedCardDTO;
    }

    @PutMapping("{boardId}/cards/{cardsId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CardsDTO updateCards(@PathVariable Long boardId, @PathVariable Long cardsId, @RequestBody CardsDTO cardsDTO){
        return cardsService.updateCardsDTO(boardId, cardsId, cardsDTO);
    }

    @PutMapping("{boardId}/cards/{cardsId}/card/{cardId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CardDTO updateCards(@PathVariable Long boardId, @PathVariable Long cardsId, @RequestBody CardDTO cardDTO, @PathVariable Long cardId){
        return cardService.updateCardDTO(cardsId, cardId, cardDTO);
    }

    @DeleteMapping("{boardId}/cards/{cardsId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCards(@PathVariable Long boardId, @PathVariable Long cardsId){
        cardsService.deleteCardsById(boardId, cardsId);
    }

    @DeleteMapping("{boardId}/cards/{cardsId}/card/{cardId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCards(@PathVariable Long boardId, @PathVariable Long cardsId, @PathVariable Long cardId){
        cardService.deleteCardById(boardId, cardsId, cardId);
    }
}
