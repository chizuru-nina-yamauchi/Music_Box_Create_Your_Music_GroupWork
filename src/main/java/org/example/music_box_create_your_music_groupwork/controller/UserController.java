package org.example.music_box_create_your_music_groupwork.controller;


import org.example.music_box_create_your_music_groupwork.model.PasswordResetToken;
import org.example.music_box_create_your_music_groupwork.model.User;
import org.example.music_box_create_your_music_groupwork.repository.PasswordResetTokenRepo;
import org.example.music_box_create_your_music_groupwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetTokenRepo passwordResetTokenRepo;



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



    @PostMapping("/forgotPassword")
        public String handleForgotPassword(@ModelAttribute User user){
        String output = "";
        User resetUser = userService.findByEmail(user.getEmail());
        if (resetUser != null){
            output = userService.sendEmail(resetUser);
        }
        if (output.equals("success")){
            return "redirect:/login?success";
        }
        return "redirect:/login?error";
    }


    @GetMapping("/resetPassword/{token}")
    public String resetPasswordForm(String token, Model model){
        PasswordResetToken reset = passwordResetTokenRepo.findByToken(token);

        if (reset != null && userService.hasExpired(reset.getExpiryDateTime())) {
            model.addAttribute("email", reset.getUser().getEmail());
            return "resetPassword";
        }
        return "redirect:/forgotPassword?error";
    }
}
