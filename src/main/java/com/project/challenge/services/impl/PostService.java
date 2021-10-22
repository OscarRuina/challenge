package com.project.challenge.services.impl;

import com.project.challenge.constants.CustomExceptionConstants;
import com.project.challenge.converters.PostConverter;
import com.project.challenge.dto.request.RequestPostDTO;
import com.project.challenge.dto.response.ResponsePostDTO;
import com.project.challenge.exceptions.EmailNotFoundException;
import com.project.challenge.exceptions.PostNotFoundException;
import com.project.challenge.models.database.Post;
import com.project.challenge.models.database.User;
import com.project.challenge.repositories.PostRepository;
import com.project.challenge.repositories.UserRepository;
import com.project.challenge.services.IPostService;
import java.time.LocalDate;
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
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Post createPost(RequestPostDTO requestPostDTO) {
        log.info("Starting creation of post from user with email {} ...",
                requestPostDTO.getEmail());
        User user;
        user = userRepository.findByEmail(requestPostDTO.getEmail()).orElseThrow(
                () -> new EmailNotFoundException(
                        CustomExceptionConstants.EMAIL_NOT_FOUND_CONTROLLER_ERROR_CODE,
                        CustomExceptionConstants.EMAIL_NOT_FOUND_CONTROLLER_ERROR_MESSAGE)
        );
        Post post = new Post();
        post.setTitle(requestPostDTO.getTitle());
        post.setContent(requestPostDTO.getContent());
        post.setImage(requestPostDTO.getImage());
        post.setCategory(requestPostDTO.getCategory());
        post.setDateOfCreation(LocalDate.now());
        post.setDelete(false);
        post.setUser(user);
        postRepository.save(post);
        return post;
    }

    @Transactional
    public Post updatePost(long idPost, RequestPostDTO requestPostDTO) {
        Post post = findById(idPost);
        log.info("update post wit id {} ", idPost);
        post.setTitle(requestPostDTO.getTitle());
        post.setContent(requestPostDTO.getContent());
        post.setImage(requestPostDTO.getImage());
        postRepository.save(post);
        return post;
    }

    @Transactional
    public String deletePost(long idPost) {
        Post post = findById(idPost);
        log.info("delete post with id {} ", idPost);
        post.setDelete(true);
        postRepository.save(post);
        log.info("Post successfully deleted");
        return "deleted";
    }

    @Transactional(readOnly = true)
    public Post findById(long idPost) {
        Post post = postRepository.findById(idPost).orElseThrow(
                () -> new PostNotFoundException(
                        CustomExceptionConstants.POST_NOT_FOUND_CONTROLLER_ERROR_CODE,
                        CustomExceptionConstants.POST_NOT_FOUND_CONTROLLER_ERROR_MESSAGE
                )
        );
        if (post.isDelete()) {
            throw new PostNotFoundException(
                    CustomExceptionConstants.POST_NOT_FOUND_CONTROLLER_ERROR_CODE,
                    CustomExceptionConstants.POST_NOT_FOUND_CONTROLLER_ERROR_MESSAGE
            );
        }
        return post;
    }

    @Transactional(readOnly = true)
    public Page<ResponsePostDTO> findPosts(PageRequest pageRequest) {
        Page<Post> post = postRepository.findAll(pageRequest);
        List<ResponsePostDTO> responsePostDTOS = new ArrayList<>();
        post.forEach(p -> responsePostDTOS.add(PostConverter.toResponsePostDTO(p)));
        return new PageImpl<>(responsePostDTOS, post.getPageable(), post.getTotalElements());
    }


}
