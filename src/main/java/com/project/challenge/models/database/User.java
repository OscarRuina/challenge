package com.project.challenge.models.database;

import com.project.challenge.constants.ErrorMessageConstants;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long idUser;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = ErrorMessageConstants.REQUIRED_PARAM_NAME_ERROR_MESSAGE)
    @Email(message = ErrorMessageConstants.INCORRECT_MAIL_ERROR_MESSAGE)
    @Size(max = 250, message = ErrorMessageConstants.MAX_SIZE_ERROR_MESSAGE)
    private String email;

    @Setter(AccessLevel.NONE)
    @Column(name = "password", nullable = false)
    @NotBlank(message = ErrorMessageConstants.REQUIRED_PARAM_NAME_ERROR_MESSAGE)
    @Size(max = 250, message = ErrorMessageConstants.MAX_SIZE_ERROR_MESSAGE)
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }
}
