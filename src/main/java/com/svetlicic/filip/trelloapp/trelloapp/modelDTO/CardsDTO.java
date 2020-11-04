package com.svetlicic.filip.trelloapp.trelloapp.modelDTO;

import com.svetlicic.filip.trelloapp.trelloapp.model.Card;
import lombok.Data;

import java.util.Set;

@Data
public class CardsDTO {

    private Long id;
    private String listName;
    private Set<Card> cards;
}
