package com.openclassrooms.mddapi.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.entity.Post;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     *
     * @param userId user id
     * @return Posts
     */

    @EntityGraph(attributePaths = {"topic", "author"})
    @Query("SELECT p FROM Topic t INNER JOIN t.subscribers u INNER JOIN t.posts p WHERE u.id = :userId")
    Iterable<Post> findPostsByUsersId(Long userId, Sort sort);


    @EntityGraph(attributePaths = {"topic", "author", "comments", "comments.author"})
    @Query("SELECT p FROM Post p WHERE p.id = :postId")
    Optional<Post> findPostByIdWithTopicWithAuthorWithComments(Long postId);
}
