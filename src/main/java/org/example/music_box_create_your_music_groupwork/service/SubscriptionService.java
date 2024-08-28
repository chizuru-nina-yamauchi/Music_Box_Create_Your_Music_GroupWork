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

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates a new subscription for the specified user.
     *
     * @param userId the ID of the user
     * @param startDate the start date of the subscription
     * @param endDate the end date of the subscription
     * @return the created subscription
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
     * Retrieves all subscriptions for a specified user.
     *
     * @param userId the ID of the user
     * @return a list of subscriptions for the user
     */
    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    /**
     * Cancels a subscription by its ID.
     *
     * @param subscriptionId the ID of the subscription to cancel
     */
    public void cancelSubscription(Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        subscriptionRepository.delete(subscription);
    }

    /**
     * Checks if a user has any active subscriptions.
     *
     * @param userId the ID of the user
     * @return true if the user has an active subscription, false otherwise
     */
    public boolean isUserSubscriber(Long userId) {
        List<Subscription> subscriptions = subscriptionRepository.findByUserId(userId);
        return subscriptions.stream().anyMatch(subscription -> subscription.getEndDate().isAfter(LocalDateTime.now()));
    }
}
