package com.project.challenge.services;

import com.project.challenge.dto.request.RequestUserDTO;
import com.project.challenge.dto.response.ResponseUserDTO;
import com.project.challenge.models.database.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IUserService {

    User createUser(RequestUserDTO requestUserDTO);

    User updateUser(long idUser, RequestUserDTO requestUserDTO);

    String deleteUser(long idUser);

    User findById(long idUser);

    Page<ResponseUserDTO> findUsers(PageRequest pageRequest);

}
