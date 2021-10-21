package com.project.challenge.converters;

import com.project.challenge.dto.response.ResponseUserDTO;
import com.project.challenge.models.database.User;

public final class UserConverter {

    private UserConverter() {
    }

    public static ResponseUserDTO toResponseUserDTO(User user) {
        ResponseUserDTO responseUserDTO = new ResponseUserDTO();
        responseUserDTO.setId(user.getIdUser());
        responseUserDTO.setEmail(user.getEmail());
        return responseUserDTO;
    }
}
