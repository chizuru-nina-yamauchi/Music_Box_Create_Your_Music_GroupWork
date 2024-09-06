package org.example.music_box_create_your_music_groupwork.controller;

import org.example.music_box_create_your_music_groupwork.model.Subscription;
import org.example.music_box_create_your_music_groupwork.model.User;
import org.example.music_box_create_your_music_groupwork.service.SubscriptionService;
import org.example.music_box_create_your_music_groupwork.service.UserService;
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
    private final UserService userService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, UserService userService) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
    }

    // Method to load the subscription form
    @GetMapping("/create")
    public String showSubscriptionForm(Model model) {
        model.addAttribute("subscription", new Subscription());
        return "subscription";  // Name of the Thymeleaf template
    }

    // Method to handle form submission and create a subscription using email
    @PostMapping("/create")
    public String createSubscription(
            @RequestParam String email,  // Accept email from the form
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate,
            Model model) {

        // Create a subscription for the user using the email
        try {
            Subscription subscription = subscriptionService.createSubscription(email, startDate, endDate);
            model.addAttribute("message", "Subscription created successfully for " + email + "!");
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "subscription";  // Return to the subscription page with a success/error message
    }

    // Method to display all subscriptions for a user based on email
    @GetMapping("/user/{email}")
    public String getSubscriptionsByEmail(@PathVariable String email, Model model) {
        try {
            List<Subscription> subscriptions = subscriptionService.getSubscriptionsByEmail(email);
            model.addAttribute("subscriptions", subscriptions);  // Add subscriptions to the model
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "subscription";  // Return the Thymeleaf template to display subscriptions
    }

    // Method to cancel a subscription
    @GetMapping("/cancel/{subscriptionId}")
    public String cancelSubscription(@PathVariable Long subscriptionId, Model model) {
        subscriptionService.cancelSubscription(subscriptionId);
        model.addAttribute("message", "Subscription canceled successfully!");
        return "redirect:/subscriptions";  // Redirect back to the subscription listing page
    }

    // Method to check if the user is a subscriber by email
    @GetMapping("/user/{email}/is-subscriber")
    public String isUserSubscriber(@PathVariable String email, Model model) {
        try {
            boolean isSubscriber = subscriptionService.isUserSubscriber(email);
            model.addAttribute("isSubscriber", isSubscriber);
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "subscription";  // Return the subscription page to show the subscription status
    }
}
