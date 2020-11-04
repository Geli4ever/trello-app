package com.svetlicic.filip.trelloapp.trelloapp.modelDTO;

import com.svetlicic.filip.trelloapp.trelloapp.model.Login;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Login login;
    private List<BoardDTO> boards = new ArrayList<>();

}
