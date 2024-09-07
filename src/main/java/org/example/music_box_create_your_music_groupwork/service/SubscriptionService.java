package org.example.music_box_create_your_music_groupwork.service;

import org.example.music_box_create_your_music_groupwork.model.Subscription;
import org.example.music_box_create_your_music_groupwork.model.User;
import org.example.music_box_create_your_music_groupwork.repository.SubscriptionRepository;
import org.example.music_box_create_your_music_groupwork.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepo userRepo;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepo userRepo) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepo = userRepo;
    }

    // Method to create a subscription for a user identified by email
    public Subscription createSubscription(String email, LocalDateTime startDate, LocalDateTime endDate) {
        // Find the user by email
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }

        // Create a new subscription
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setStartDate(startDate);
        subscription.setEndDate(endDate);

        // Save the subscription to the repository
        return subscriptionRepository.save(subscription);
    }

    // Method to get all subscriptions for a user identified by email
    public List<Subscription> getSubscriptionsByEmail(String email) {
        // Find the user by email
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }

        // Retrieve subscriptions by user ID
        return subscriptionRepository.findByUserId(user.getId());
    }

    // Method to cancel a subscription by its ID
    public void cancelSubscription(Long subscriptionId) {
        // Find the subscription by ID
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        // Delete the subscription from the repository
        subscriptionRepository.delete(subscription);
    }

    // Method to check if a user is a subscriber based on email
    public boolean isUserSubscriber(String email) {
        // Find the user by email
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }

        // Retrieve subscriptions by user ID
        List<Subscription> subscriptions = subscriptionRepository.findByUserId(user.getId());

        // Check if any subscription is still active
        return subscriptions.stream().anyMatch(subscription -> subscription.getEndDate().isAfter(LocalDateTime.now()));
    }
}