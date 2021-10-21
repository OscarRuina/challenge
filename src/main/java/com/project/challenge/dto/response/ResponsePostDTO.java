package com.project.challenge.dto.response;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@JsonRootName("post")
@Getter
@Setter
public class ResponsePostDTO {

    private long id;
    private String title;
    private String content;
    private String image;
    private String category;
    private LocalDate dateOfCreation;


}
