package com.project.challenge.exceptions;

import org.springframework.http.HttpStatus;

public class PostNotFoundException extends GeneralApiException {

    private final String code;

    public PostNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    @Override
    public String getErrorCode() {
        return code;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
