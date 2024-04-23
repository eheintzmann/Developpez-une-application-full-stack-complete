package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.entity.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    /**
     *
     * @return Topics
     */
    @Query("Select t From Topic t left join t.subscribers")
    Iterable<Topic> findAllTopicsWithSubscribers();
}
