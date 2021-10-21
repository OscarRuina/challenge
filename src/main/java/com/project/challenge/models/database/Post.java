package com.project.challenge.models.database;

import com.project.challenge.constants.ErrorMessageConstants;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "post")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @Column(name = "title")
    @NotBlank(message = ErrorMessageConstants.REQUIRED_PARAM_NAME_ERROR_MESSAGE)
    @Size(max = 250, message = ErrorMessageConstants.MAX_SIZE_ERROR_MESSAGE)
    private String title;

    @Column(name = "content")
    @NotBlank(message = ErrorMessageConstants.REQUIRED_PARAM_NAME_ERROR_MESSAGE)
    @Size(max = 500, message = ErrorMessageConstants.MAX_SIZE_ERROR_MESSAGE)
    private String content;

    @Column(name = "image")
    @NotBlank(message = ErrorMessageConstants.REQUIRED_PARAM_NAME_ERROR_MESSAGE)
    private String image;

    @Column(name = "category")
    @NotBlank(message = ErrorMessageConstants.REQUIRED_PARAM_NAME_ERROR_MESSAGE)
    @Size(max = 250, message = ErrorMessageConstants.MAX_SIZE_ERROR_MESSAGE)
    private String category;

    @Column(name = "dateOfCreation")
    @CreatedDate
    private LocalDate dateOfCreation;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

}
