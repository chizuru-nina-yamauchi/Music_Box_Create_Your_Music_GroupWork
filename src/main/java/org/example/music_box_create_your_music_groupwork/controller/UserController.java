package org.example.music_box_create_your_music_groupwork.controller;

import org.example.music_box_create_your_music_groupwork.model.Subscription;
import org.example.music_box_create_your_music_groupwork.model.User;
import org.example.music_box_create_your_music_groupwork.service.UserService;
import org.example.music_box_create_your_music_groupwork.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/subscribe")
    public String showSubscriberForm(Model model) {
        model.addAttribute("subscription", new Subscription());  // Assuming you have a Subscription entity
        return "subscriptionForm";  // Return the view containing the subscription form
    }

    @PostMapping("/subscribe")
    public String processSubscription(@ModelAttribute Subscription subscription, Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());

        if (user != null) {
            // Assign the subscriber role to the user
            userService.addRoleToUser(user, "ROLE_SUBSCRIBER");

            // Create a new subscription and save it
            subscription.setUser(user);
            subscription.setStartDate(LocalDateTime.now());
            subscription.setEndDate(LocalDateTime.now().plusMonths(1));  // Example for a 1-month subscription
            subscriptionService.createSubscription(subscription);

            model.addAttribute("message", "Subscription successful! You are now a premium user.");
            return "redirect:/subscriber-home";  // Redirect to the subscriber home page
        }

        model.addAttribute("errorMessage", "Subscription failed. Please try again.");
        return "subscriptionForm";
    }
}