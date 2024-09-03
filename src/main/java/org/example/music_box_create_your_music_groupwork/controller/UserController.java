package org.example.music_box_create_your_music_groupwork.controller;


import org.example.music_box_create_your_music_groupwork.model.Subscription;
import org.example.music_box_create_your_music_groupwork.model.User;
import org.example.music_box_create_your_music_groupwork.service.SubscriptionService;
import org.example.music_box_create_your_music_groupwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private SubscriptionService subscriptionService;



    // home page
    @GetMapping("/")
    public String homepage(Principal principal){
        User user = userService.findByUsername(principal.getName());
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_SUBSCRIBER"))) {
            return "redirect:/subscriber-home";
        }
        return "homepage";
    }


    // login
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("errorMessage","Invalid Credentials");
        return "login"; // returns to the login view
    }


    // logout
    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login?logout"; // Redirect to login page
    }


    // signup form
    @GetMapping("/signup")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }


    // signup
    @PostMapping("/signup")
    public String signup(User user, Model model){
        boolean isUserSaved = userService.saveUser(user);
        if (!isUserSaved){
            model.addAttribute("errorMessage", "Failed to send verification email. Please add a correct email.");
            return "redirect:/signup";
        }
        model.addAttribute("message","A verification email has been send to " + user.getEmail());
        return "verificationReq";
    }


    // verifying user
    @GetMapping("/verify")
    public String verifyUser(@RequestParam("token") String token){
        boolean isVerified = userService.verifyUser(token);
        if (isVerified){
            return "redirect:/login?verified";
        }
        return "signup";
    }




    //forgot password
    @GetMapping("/forgotPassword")
    public String forgotPasswordForm(){
        return "forgotPassword";
    }


  /*  @PostMapping("/forgotPassword")
    public String handleForgotPassword(){
        // logic
        // check if e mail exists
        // check if security answer is correct
        //send email
        //userService.sendPasswordResetEmail(email) bsp
    }*/



     //Ioannis
    // subscriber form
    @GetMapping("/subscribe")
    public String showSubscriberForm(Model model) {
        model.addAttribute("subscription", new Subscription());
        return "subscriptionForm";  // This should map to your subscription form view
    }

    // assign Subscriber
    @PostMapping("/subscribe")
    public String processSubscription(@ModelAttribute Subscription subscription, Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());

        if (user != null) {
            userService.addRoleToUser(user, "ROLE_SUBSCRIBER");

            subscription.setUser(user);
            subscription.setStartDate(LocalDateTime.now());
            subscription.setEndDate(LocalDateTime.now().plusMonths(1));
            subscriptionService.createSubscription(user.getId(), subscription.getStartDate(), subscription.getEndDate());

            model.addAttribute("message", "Subscription successful! You are now a premium user.");
            return "redirect:/subscriber-home";
        }

        model.addAttribute("errorMessage", "Subscription failed. Please try again.");
        return "subscriptionForm";
    }
}