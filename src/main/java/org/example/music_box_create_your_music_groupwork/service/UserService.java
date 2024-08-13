package org.example.music_box_create_your_music_groupwork.service;

import org.example.music_box_create_your_music_groupwork.model.Subscription;
import org.example.music_box_create_your_music_groupwork.model.User;
import org.example.music_box_create_your_music_groupwork.repository.RoleRepository;
import org.example.music_box_create_your_music_groupwork.repository.SubscriptionRepository;
import org.example.music_box_create_your_music_groupwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Service class for managing users and their subscriptions.
 * Provides methods for creating, updating, retrieving, and deleting users,
 * as well as creating subscriptions for users.
 */
@Service
public class UserService {

    /**
     * Repository for managing User entities.
     */
    private final UserRepository userRepository;

    /**
     * Repository for managing Role entities.
     */
    private final RoleRepository roleRepository;

    /**
     * Repository for managing Subscription entities.
     */
    private final SubscriptionRepository subscriptionRepository;

    /**
     * Constructs a new UserService with the specified repositories.
     *
     * @param userRepository the UserRepository to use for user-related operations
     * @param roleRepository the RoleRepository to use for role-related operations
     * @param subscriptionRepository the SubscriptionRepository to use for subscription-related operations
     */
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.subscriptionRepository = subscriptionRepository;
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
}