package org.example.music_box_create_your_music_groupwork.controller;

import org.example.music_box_create_your_music_groupwork.model.Subscription;
import org.example.music_box_create_your_music_groupwork.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/create")
    public ResponseEntity<Subscription> createSubscription(
            @RequestParam Long userId,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        Subscription subscription = subscriptionService.createSubscription(userId, startDate, endDate);
        return ResponseEntity.ok(subscription);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Subscription>> getSubscriptionsByUserId(@PathVariable Long userId) {
        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByUserId(userId);
        return ResponseEntity.ok(subscriptions);
    }

    @DeleteMapping("/cancel/{subscriptionId}")
    public ResponseEntity<Void> cancelSubscription(@PathVariable Long subscriptionId) {
        subscriptionService.cancelSubscription(subscriptionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}/is-subscriber")
    public ResponseEntity<Boolean> isUserSubscriber(@PathVariable Long userId) {
        boolean isSubscriber = subscriptionService.isUserSubscriber(userId);
        return ResponseEntity.ok(isSubscriber);
    }
}
