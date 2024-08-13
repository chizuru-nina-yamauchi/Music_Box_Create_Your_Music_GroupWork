package org.example.music_box_create_your_music_groupwork.model;


import jakarta.persistence.*;

import java.util.Set;

/**
 * Entity class representing a User in the system.
 * This class is mapped to a database table using JPA annotations.
 */
@Entity
public class User {

    /**
     * The unique identifier for the user.
     * This value is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The username of the user.
     * This value must be unique and cannot be null.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * The password of the user.
     * This value cannot be null.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The email address of the user.
     * This value must be unique and cannot be null.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * The status of the user.
     * This value cannot be null.
     */
    @Column(nullable = false)
    private String status;

    /**
     * The subscriptions associated with the user.
     * This is a one-to-many relationship.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Subscription> subscriptions;

    // Getters and Setters

    /**
     * Gets the ID of the user.
     *
     * @return the ID of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id the new ID of the user
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the new username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the new password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email of the user.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email the new email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the status of the user.
     *
     * @return the status of the user
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the user.
     *
     * @param status the new status of the user
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the subscriptions of the user.
     *
     * @return the subscriptions of the user
     */
    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }

    /**
     * Sets the subscriptions of the user.
     *
     * @param subscriptions the new subscriptions of the user
     */
    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}