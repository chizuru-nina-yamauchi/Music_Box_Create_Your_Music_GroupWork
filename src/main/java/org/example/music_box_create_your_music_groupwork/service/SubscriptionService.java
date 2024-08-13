package org.example.music_box_create_your_music_groupwork.service;

import org.example.music_box_create_your_music_groupwork.model.Subscription;
import org.example.music_box_create_your_music_groupwork.model.User;
import org.example.music_box_create_your_music_groupwork.repository.SubscriptionRepository;
import org.example.music_box_create_your_music_groupwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class for managing subscriptions.
 * Provides methods for creating, retrieving, and canceling subscriptions.
 */
@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    /**
     * Constructs a SubscriptionService with the specified repositories.
     *
     * @param subscriptionRepository the SubscriptionRepository to be used by this service
     * @param userRepository the UserRepository to be used by this service
     */
    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates a new subscription for a user.
     *
     * @param userId the ID of the user for whom the subscription is to be created
     * @param startDate the start date of the subscription
     * @param endDate the end date of the subscription
     * @return the created Subscription
     * @throws RuntimeException if the user is not found
     */
    public Subscription createSubscription(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setStartDate(startDate);
        subscription.setEndDate(endDate);

        return subscriptionRepository.save(subscription);
    }

    /**
     * Retrieves a list of subscriptions for a user.
     *
     * @param userId the ID of the user whose subscriptions are to be retrieved
     * @return a list of Subscriptions associated with the given user ID
     */
    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    /**
     * Cancels a subscription.
     *
     * @param subscriptionId the ID of the subscription to be canceled
     * @throws RuntimeException if the subscription is not found
     */
    public void cancelSubscription(Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        subscriptionRepository.delete(subscription);
    }

    // Additional methods can be added as needed
}