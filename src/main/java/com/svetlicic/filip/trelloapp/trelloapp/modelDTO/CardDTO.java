package com.svetlicic.filip.trelloapp.trelloapp.modelDTO;

import lombok.Data;

@Data
public class CardDTO {

    private Long id;
    private Long boardId;
    private Long cardsId;
    private String description;
    private String keyString;
}
