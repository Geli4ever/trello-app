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
}
