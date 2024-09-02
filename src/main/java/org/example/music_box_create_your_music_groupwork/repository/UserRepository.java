package org.example.music_box_create_your_music_groupwork.repository;


import org.example.music_box_create_your_music_groupwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing User entities.
 * Provides methods for performing CRUD operations on User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user to find
     * @return the user with the specified username, or null if no user is found
     */
    User findByUsername(String username);

    /**
     * Finds a user by their email.
     *
     * @param email the email of the user to find
     * @return the user with the specified email, or null if no user is found
     */
    User findByEmail(String email);



}
