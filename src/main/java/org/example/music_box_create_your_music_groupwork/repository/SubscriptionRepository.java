package org.example.music_box_create_your_music_groupwork.repository;

import org.example.music_box_create_your_music_groupwork.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for managing Subscription entities.
 * Provides methods for performing CRUD operations on Subscription entities.
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    /**
     * Finds subscriptions by the user ID.
     *
     * @param userId the ID of the user
     * @return a list of subscriptions for the specified user
     */
    List<Subscription> findByUserId(Long userId);

    /**
     * Finds subscriptions that end after the specified date.
     *
     * @param date the date to compare with the end date of subscriptions
     * @return a list of subscriptions that end after the specified date
     */
    List<Subscription> findByEndDateAfter(LocalDateTime date);
}