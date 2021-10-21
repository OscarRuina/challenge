package com.project.challenge.services.impl;

import com.project.challenge.constants.CustomExceptionConstants;
import com.project.challenge.converters.UserConverter;
import com.project.challenge.dto.request.RequestUserDTO;
import com.project.challenge.dto.response.ResponseUserDTO;
import com.project.challenge.exceptions.EmailAlreadyTakenException;
import com.project.challenge.exceptions.UserNotFoundException;
import com.project.challenge.models.database.User;
import com.project.challenge.repositories.UserRepository;
import com.project.challenge.services.IUserService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(RequestUserDTO requestUserDTO) {
        log.info("Starting the creation of user with email {} ...", requestUserDTO.getEmail());
        userRepository.findByEmail(requestUserDTO.getEmail()).ifPresent(users -> {
            throw new EmailAlreadyTakenException(
                    CustomExceptionConstants.USER_EMAIL_ALREADY_TAKEN_ERROR_CODE,
                    CustomExceptionConstants.USER_EMAIL_ALREADY_TAKEN_ERROR_MESSAGE);
        });
        User user = new User();
        user.setEmail(requestUserDTO.getEmail());
        user.setPassword(requestUserDTO.getPassword());
        userRepository.save(user);
        return user;
    }

    @Transactional
    public User updateUser(long idUser, RequestUserDTO requestUserDTO) {
        User user = findById(idUser);
        log.info("Update user with id {} and email {} ", idUser, user.getEmail());
        user.setEmail(requestUserDTO.getEmail());
        userRepository.save(user);
        return user;
    }

    @Transactional
    public String deleteUser(long idUser) {
        User user = findById(idUser);
        log.info("Deleting user with id {} and email {}", idUser, user.getEmail());
        userRepository.deleteById(idUser);
        log.info("User delete Successfully");
        return "deleted";
    }

    @Transactional(readOnly = true)
    public User findById(long idUser) {
        return userRepository.findById(idUser).orElseThrow(
                () -> new UserNotFoundException(
                        CustomExceptionConstants.USER_NOT_FOUND_CONTROLLER_ERROR_CODE,
                        CustomExceptionConstants.USER_NOT_FOUND_CONTROLLER_ERROR_MESSAGE)
        );
    }

    @Transactional(readOnly = true)
    public Page<ResponseUserDTO> findUsers(PageRequest pageRequest) {
        Page<User> users = userRepository.findAll(pageRequest);
        List<ResponseUserDTO> responseUserDTOS = new ArrayList<>();
        users.forEach(u -> responseUserDTOS.add(UserConverter.toResponseUserDTO(u)));
        return new PageImpl<>(responseUserDTOS, users.getPageable(), users.getTotalElements());
    }
}
