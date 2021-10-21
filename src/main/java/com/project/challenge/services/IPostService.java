package com.project.challenge.services;

import com.project.challenge.dto.request.RequestPostDTO;
import com.project.challenge.models.database.Post;

public interface IPostService {

    Post createPost(RequestPostDTO requestPostDTO);

}
