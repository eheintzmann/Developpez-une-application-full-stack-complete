package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     *
     * @param userId user id
     * @return Topics
     */
    @Query("Select p From Topic t inner join t.subscribers u inner join t.posts p where u.id = :userId")
    Iterable<Post> findPostsByUsersId(Long userId);
}
