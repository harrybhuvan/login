package com.example.login.mapper;

import com.example.login.DTO.UserDTO;
import com.example.login.entity.User;

public class UserMapper {

    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setNumber(dto.getNumber());
        return user;
    }

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setNumber(user.getNumber());
        return dto;
    }
}

