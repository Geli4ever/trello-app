package com.svetlicic.filip.trelloapp.trelloapp.mapper;

import com.svetlicic.filip.trelloapp.trelloapp.model.User;
import com.svetlicic.filip.trelloapp.trelloapp.modelDTO.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = BoardMapper.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "login.user", ignore = true)
    UserDTO userToUserDTO(User user);

    @Mapping(target = "login.user", ignore = true)
    User userDtoToUser(UserDTO userDTO);
}
