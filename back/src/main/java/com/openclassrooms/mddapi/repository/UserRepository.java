package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.entity.Topic;
import com.openclassrooms.mddapi.model.entity.User;
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
     * @return Optional<User>
     */
    Optional<User> findByEmail(String email);

    /**
     * Check user existence
     *
     * @param email user email
     * @return boolean
     */
    boolean existsByEmailOrUsername(String email, String username);

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
    @Query("Select u From User u left join u.subscriptions t where u.id = :userId")
    Optional<User> findUsersByIdWithSubscriptions(Long userId);
}
