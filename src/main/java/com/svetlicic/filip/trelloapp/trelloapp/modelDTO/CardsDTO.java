package com.svetlicic.filip.trelloapp.trelloapp.modelDTO;

import lombok.Data;

import java.util.Set;

@Data
public class CardsDTO {

    private Long id;
    private Long boardId;
    private String listName;
    private String keyString;
    private Set<CardDTO> cards;
}
