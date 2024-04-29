package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.entity.Topic;
import com.openclassrooms.mddapi.model.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by his email
     *
     * @param email user email
     * @return Optional User
     */
    Optional<User> findByEmail(String email);

    /**
     *
     * @param username username
     * @return Optional User
     */
    Optional<User> findByUsername(String username);


    /**
     * Check user existence
     *
     * @param email user email
     * @return boolean
     */
    boolean existsByEmail(String email);

    /**
     * Check user existence
     *
     * @param username username
     * @return boolean
     */
    boolean existsByUsername(String username);

    /**
     *
     * @param userId user id
     * @return Topics
     */
    @Query("Select u.subscriptions From User u where u.id = :userId")
    Iterable<Topic> findSubscriptionsByUser(Long userId);

    /**
     *
     * @param userId user Id
     * @return User
     */

    @EntityGraph(attributePaths = {"subscriptions"})
    @Query("Select u From User u where u.id = :userId")
    Optional<User> findUserByIdWithSubscriptions(Long userId);
}
