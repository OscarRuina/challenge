package com.project.challenge.controllers;

import com.project.challenge.converters.UserConverter;
import com.project.challenge.dto.request.RequestUserDTO;
import com.project.challenge.dto.response.ResponseUserDTO;
import com.project.challenge.models.response.ApplicationResponse;
import com.project.challenge.services.IUserService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {

    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping("/sign_up")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationResponse<ResponseUserDTO> signup(
            @Valid @RequestBody RequestUserDTO requestUserDTO) {
        log.info("POST/auth/signup");
        ResponseUserDTO responseUserDTO = UserConverter.toResponseUserDTO(
                iUserService.createUser(requestUserDTO));
        return new ApplicationResponse<>(responseUserDTO, null);
    }
}
