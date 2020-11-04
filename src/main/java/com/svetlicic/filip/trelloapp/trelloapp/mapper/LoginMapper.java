package com.svetlicic.filip.trelloapp.trelloapp.mapper;

import com.svetlicic.filip.trelloapp.trelloapp.model.Login;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.LoginDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoginMapper {
    LoginMapper INSTANCE = Mappers.getMapper(LoginMapper.class);

    Login loginDtoToLogin(LoginDTO loginDTO);

    LoginDTO loginToLoginDTO(Login login);
}
