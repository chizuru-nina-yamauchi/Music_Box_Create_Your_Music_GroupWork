package org.example.music_box_create_your_music_groupwork.controller;

import org.example.music_box_create_your_music_groupwork.model.User;
import org.example.music_box_create_your_music_groupwork.repository.RoleRepo;
import org.example.music_box_create_your_music_groupwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class UserController {


    @Autowired
    private UserService userService;

    //@Autowired
    //private RoleRepo roleRepo;


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
        return "/signup";
    }


    // subscriber form
    // Ioannis Part


    // assign Subscriber
    // Ioannis Part

}
