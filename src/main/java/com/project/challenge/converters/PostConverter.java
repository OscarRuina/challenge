package com.project.challenge.converters;

import com.project.challenge.dto.response.ResponsePostDTO;
import com.project.challenge.models.database.Post;

public class PostConverter {

    private PostConverter() {
    }

    public static ResponsePostDTO toResponsePostDTO(Post post) {
        ResponsePostDTO responsePostDTO = new ResponsePostDTO();
        responsePostDTO.setId(post.getIdPost());
        responsePostDTO.setTitle(post.getTitle());
        responsePostDTO.setContent(post.getContent());
        responsePostDTO.setImage(post.getImage());
        responsePostDTO.setCategory(post.getCategory());
        responsePostDTO.setDateOfCreation(post.getDateOfCreation());
        return responsePostDTO;
    }

}
