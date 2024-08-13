package org.example.music_box_create_your_music_groupwork.repository;

import org.example.music_box_create_your_music_groupwork.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for managing Subscription entities.
 * Extends JpaRepository to provide CRUD operations and additional query methods.
 */
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    /**
     * Finds a list of Subscriptions by the user ID.
     *
     * @param userId the ID of the user whose subscriptions are to be found
     * @return a list of Subscriptions associated with the given user ID
     */
    List<Subscription> findByUserId(Long userId);
}