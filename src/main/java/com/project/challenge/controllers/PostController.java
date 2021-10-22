package com.project.challenge.controllers;

import com.project.challenge.converters.PostConverter;
import com.project.challenge.dto.request.RequestPostDTO;
import com.project.challenge.dto.response.ResponsePostDTO;
import com.project.challenge.models.response.ApplicationResponse;
import com.project.challenge.services.IPostService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PostController {

    private final IPostService iPostService;

    public PostController(IPostService iPostService) {
        this.iPostService = iPostService;
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationResponse<ResponsePostDTO> createPost(
            @Valid @RequestBody RequestPostDTO requestPostDTO) {
        log.info("POST/");
        ResponsePostDTO responsePostDTO = PostConverter.toResponsePostDTO(
                iPostService.createPost(requestPostDTO));
        log.info("Post successfully created");
        return new ApplicationResponse<>(responsePostDTO, null);
    }

    @GetMapping("/posts/{idPost}")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationResponse<ResponsePostDTO> getPost(@PathVariable("idPost") long idPost) {
        log.info("GET/posts/{idPost}", idPost);
        ResponsePostDTO responsePostDTO = PostConverter.toResponsePostDTO(
                iPostService.findById(idPost)
        );
        return new ApplicationResponse<>(responsePostDTO, null);
    }

    @DeleteMapping("/posts/{idPost}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApplicationResponse<String>> deletePost(
            @PathVariable("idPost") long idPost) {
        log.info("DELETE/posts/{idPost}", idPost);
        ApplicationResponse<String> applicationResponse =
                new ApplicationResponse<>(
                        iPostService.deletePost(idPost), null
                );
        log.info("Post successfully deleted");
        return ResponseEntity.ok().body(applicationResponse);
    }

    @PutMapping("/posts/{idPost}")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationResponse<ResponsePostDTO> updatePost(@PathVariable("idPost") long idPost,
            @Valid @RequestBody RequestPostDTO requestPostDTO) {
        log.info("PUT/posts/{idPost}", idPost);
        ResponsePostDTO responsePostDTO = PostConverter.toResponsePostDTO(
                iPostService.updatePost(idPost, requestPostDTO));
        log.info("Post successfully updated");
        return new ApplicationResponse<>(responsePostDTO, null);
    }

    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApplicationResponse<Page<ResponsePostDTO>>> getPosts() {
        Page<ResponsePostDTO> responsePostDTOS;
        PageRequest pageRequest = PageRequest.of(0, 25);
        responsePostDTOS = iPostService.findPosts(pageRequest);
        ApplicationResponse<Page<ResponsePostDTO>> applicationResponse = new ApplicationResponse<>(
                responsePostDTOS, null);
        return ResponseEntity.ok(applicationResponse);
    }

}
