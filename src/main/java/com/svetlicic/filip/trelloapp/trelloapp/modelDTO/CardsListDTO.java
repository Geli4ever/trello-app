package com.svetlicic.filip.trelloapp.trelloapp.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CardsListDTO {
    private List<CardsDTO> cardsSet;
}
