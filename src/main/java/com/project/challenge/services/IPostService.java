package com.project.challenge.services;

import com.project.challenge.dto.request.RequestPostDTO;
import com.project.challenge.dto.response.ResponsePostDTO;
import com.project.challenge.models.database.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IPostService {

    Post createPost(RequestPostDTO requestPostDTO);

    Post updatePost(long idPost, RequestPostDTO requestPostDTO);

    String deletePost(long idPost);

    Post findById(long idPost);

    Page<ResponsePostDTO> findPosts(PageRequest pageRequest);

}
