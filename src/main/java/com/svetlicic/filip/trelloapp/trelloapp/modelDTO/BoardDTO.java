package com.svetlicic.filip.trelloapp.trelloapp.modelDTO;

import lombok.Data;

@Data
public class BoardDTO {
    private Long id;
    private Long userId;
    private String boardName;
    private String keyString;
}
