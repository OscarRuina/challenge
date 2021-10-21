package com.project.challenge.dto.request;

import com.project.challenge.constants.ErrorMessageConstants;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RequestPostDTO {

    @NotBlank(message = ErrorMessageConstants.REQUIRED_PARAM_NAME_ERROR_MESSAGE)
    @Size(max = 250, message = ErrorMessageConstants.MAX_SIZE_ERROR_MESSAGE)
    private String title;

    @NotBlank(message = ErrorMessageConstants.REQUIRED_PARAM_NAME_ERROR_MESSAGE)
    @Size(max = 500, message = ErrorMessageConstants.MAX_SIZE_ERROR_MESSAGE)
    private String content;

    @NotBlank(message = ErrorMessageConstants.REQUIRED_PARAM_NAME_ERROR_MESSAGE)
    private String image;

    @NotBlank(message = ErrorMessageConstants.REQUIRED_PARAM_NAME_ERROR_MESSAGE)
    @Size(max = 250, message = ErrorMessageConstants.MAX_SIZE_ERROR_MESSAGE)
    private String category;

    @NotBlank(message = ErrorMessageConstants.REQUIRED_PARAM_NAME_ERROR_MESSAGE)
    @Email(message = ErrorMessageConstants.INCORRECT_MAIL_ERROR_MESSAGE)
    @Size(max = 250, message = ErrorMessageConstants.MAX_SIZE_ERROR_MESSAGE)
    private String email;

}
