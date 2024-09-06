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

    public Subscription createSubscription(String email, LocalDateTime startDate, LocalDateTime endDate) {
        // Find the user by email
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setStartDate(startDate);
        subscription.setEndDate(endDate);

        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getSubscriptionsByEmail(String email) {
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }

        return subscriptionRepository.findByUserId(user.getId());
    }

    public void cancelSubscription(Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        subscriptionRepository.delete(subscription);
    }

    public boolean isUserSubscriber(String email) {
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }

        List<Subscription> subscriptions = subscriptionRepository.findByUserId(user.getId());
        return subscriptions.stream().anyMatch(subscription -> subscription.getEndDate().isAfter(LocalDateTime.now()));
    }
}
