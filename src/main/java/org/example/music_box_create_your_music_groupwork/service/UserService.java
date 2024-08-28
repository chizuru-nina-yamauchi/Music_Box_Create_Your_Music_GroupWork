package org.example.music_box_create_your_music_groupwork.service;

import org.example.music_box_create_your_music_groupwork.model.Role;
import org.example.music_box_create_your_music_groupwork.model.Subscription;
import org.example.music_box_create_your_music_groupwork.model.User;
import org.example.music_box_create_your_music_groupwork.repository.RoleRepository;
import org.example.music_box_create_your_music_groupwork.repository.SubscriptionRepository;
import org.example.music_box_create_your_music_groupwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class for managing users and their subscriptions.
 * Provides methods for creating, updating, retrieving, and deleting users,
 * as well as creating subscriptions for users.
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user to find
     * @return the found user
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Creates a new user.
     *
     * @param user the user to create
     * @return the created user
     */
    public User createUser(User user) {
        user.setStatus("ACTIVE");
        return userRepository.save(user);
    }

    /**
     * Creates a new subscription for the specified user.
     *
     * @param user the user to create the subscription for
     * @param startDate the start date of the subscription
     * @param endDate the end date of the subscription
     * @return the created subscription
     */
    public Subscription createSubscription(User user, LocalDateTime startDate, LocalDateTime endDate) {
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setStartDate(startDate);
        subscription.setEndDate(endDate);
        return subscriptionRepository.save(subscription);
    }

    /**
     * Adds a role to a user.
     *
     * @param user the user to whom the role is to be added
     * @param roleName the name of the role to add
     */
    public void addRoleToUser(User user, String roleName) {
        Optional<Role> roleOptional = roleRepository.findByName(roleName);
        if (roleOptional.isPresent()) {
            Role role = roleOptional.get();
            user.getRoles().add(role);
            userRepository.save(user);  // Save the user with the updated roles
        }
    }

    /**
     * Finds a user by their ID.
     *
     * @param id the ID of the user to find
     * @return an Optional containing the found user, or empty if no user is found
     */
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Updates the specified user.
     *
     * @param user the user to update
     * @return the updated user
     */
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes the user with the specified ID.
     *
     * @param id the ID of the user to delete
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Set<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}