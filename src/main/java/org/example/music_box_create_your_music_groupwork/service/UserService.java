package org.example.music_box_create_your_music_groupwork.service;

import jakarta.transaction.Transactional;
import org.example.music_box_create_your_music_groupwork.model.Role;
import org.example.music_box_create_your_music_groupwork.model.User;
import org.example.music_box_create_your_music_groupwork.model.VerificationToken;
import org.example.music_box_create_your_music_groupwork.repository.RoleRepo;
import org.example.music_box_create_your_music_groupwork.repository.UserRepo;
import org.example.music_box_create_your_music_groupwork.repository.VerificationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    // dependencies
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepo verificationTokenRepo;

    @Autowired
    private JavaMailSender mailSender;



    //
    // all about the user
    //



    // load user by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        // handles the case if no user is found
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // log user details
        System.out.println("User found: " + user.getUsername() + ", Verified: " + user.isVerified() + ", Roles: " + user.getRoles());

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())) // Convert roles to authorities
                .accountLocked(!user.isVerified())
                .build();
    }



    // save new user
    @Transactional
    public boolean saveUser(User user){
        // encode the password of the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepo.findRoleByName("ROLE_USER");
        // if no user role is found -> create new
        if (userRole == null){
            userRole = new Role("ROLE_USER");
            roleRepo.save(userRole);
        }
        roles.add(userRole);
        user.setRoles(roles);
        userRole.addUser(user); // Ensure bidirectional relationship

        try {
            userRepo.save(user);
            sendVerificationEmail(user);
        } catch (MailException e){
            System.out.println("Failed to send verification E-Mail:" +e.getMessage());
            return false;
        }
        return true;
    }



    // update user roles
    @Transactional
    public void updateUserRoles(User user){
        userRepo.save(user);
    }



    // find user by username
    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }



    // find by email
    public User findByEmail(String email){
        return userRepo.findByEmail(email);
    }



    // verify a user
    public boolean verifyUser(String token){
        VerificationToken verificationToken = getVerificationToken(token);
        if (verificationToken != null // does exist
                && verificationToken.getExpiryDate().isAfter(LocalDateTime.now()) // not expired
        ) {
            User user = verificationToken.getUser();
            user.setVerified(true);
            userRepo.save(user);
            verificationTokenRepo.delete(verificationToken); // we might keep it for our future logs
            // Log verification success
            System.out.println("User verified: " + user.getUsername());
            return true;

        } else {
            // Log verification failure
            System.out.println("Verification-token is invalid or expired.");
            return false;
        }
    }



    //
    // all about the verification token
    //


    // send a verification email
    private void sendVerificationEmail(User user) throws MailException {
        String token = UUID.randomUUID().toString();
        createOrUpdateVerificationToken(user, token);
        String recipientAddress = user.getEmail();
        String subject = "Email Verification";
        String confirmationUrl = "http://localhost:7070/verify?token=" + token;
        String message = "Please click the link below to verify your email address:\n" + confirmationUrl;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        try {
            mailSender.send(email);
        } catch (Exception e){
            System.out.println("Failed to send E-Mail." + e.getMessage());
        }

    }


    // create or update a verification token
    private void createOrUpdateVerificationToken(User user, String token) {
        VerificationToken verificationToken = verificationTokenRepo.findByUsername(user.getUsername());
        if (verificationToken == null) {
            verificationToken = new VerificationToken();
            verificationToken.setUser(user);
        }
        verificationToken.setToken(token);
        verificationToken.setExpiryDate(LocalDateTime.now().plusDays(1));
        verificationTokenRepo.save(verificationToken);
    }


    // get verification token
    public VerificationToken getVerificationToken(String token) {
        return verificationTokenRepo.findByToken(token);
    }



}
