package org.example.music_box_create_your_music_groupwork.controller;

import org.example.music_box_create_your_music_groupwork.model.Subscription;
import org.example.music_box_create_your_music_groupwork.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    // Method to load the subscription form
    @GetMapping("/create")
    public String showSubscriptionForm(Model model) {
        model.addAttribute("subscription", new Subscription());
        return "subscription";  // Name of the Thymeleaf template
    }

    // Method to handle form submission and create a subscription
    @PostMapping("/create")
    public String createSubscription(
            @RequestParam Long userId,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate,
            Model model) {
        Subscription subscription = subscriptionService.createSubscription(userId, startDate, endDate);
        model.addAttribute("message", "Subscription created successfully!");
        return "subscription";  // Return to the subscription page with a success message
    }

    // Method to display all subscriptions for a user
    @GetMapping("/user/{userId}")
    public String getSubscriptionsByUserId(@PathVariable Long userId, Model model) {
        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByUserId(userId);
        model.addAttribute("subscriptions", subscriptions);  // Add subscriptions to the model
        return "subscription";  // Return the Thymeleaf template to display subscriptions
    }

    // Method to cancel a subscription
    @GetMapping("/cancel/{subscriptionId}")
    public String cancelSubscription(@PathVariable Long subscriptionId, Model model) {
        subscriptionService.cancelSubscription(subscriptionId);
        model.addAttribute("message", "Subscription canceled successfully!");
        return "redirect:/subscriptions/user/{subscriptionId}";  // Redirect back to the subscription listing page
    }

    // Method to check if the user is a subscriber
    @GetMapping("/user/{userId}/is-subscriber")
    public String isUserSubscriber(@PathVariable Long userId, Model model) {
        boolean isSubscriber = subscriptionService.isUserSubscriber(userId);
        model.addAttribute("isSubscriber", isSubscriber);
        return "subscription";  // Return the subscription page to show the subscription status
    }
}
