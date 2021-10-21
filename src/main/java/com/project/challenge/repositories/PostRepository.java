package com.project.challenge.repositories;

import com.project.challenge.models.database.Post;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(long idPost);

    @Query()
    Page<Post> findByFilter(@Param("query") String query,
            @Param("active") boolean active,
            Pageable pageable);

}
