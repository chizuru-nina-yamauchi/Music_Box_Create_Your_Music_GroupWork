package org.example.music_box_create_your_music_groupwork.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Entity class representing a Subscription in the system.
 * This class is mapped to a database table using JPA annotations.
 */
@Entity
public class Subscription {

    /**
     * The unique identifier for the subscription.
     * This value is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The start date of the subscription.
     * This value cannot be null.
     */
    @Column(nullable = false)
    private LocalDateTime startDate;

    /**
     * The end date of the subscription.
     * This value cannot be null.
     */
    @Column(nullable = false)
    private LocalDateTime endDate;

    /**
     * The user associated with the subscription.
     * This is a many-to-one relationship.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and Setters

    /**
     * Gets the ID of the subscription.
     *
     * @return the ID of the subscription
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the subscription.
     *
     * @param id the new ID of the subscription
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the start date of the subscription.
     *
     * @return the start date of the subscription
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the subscription.
     *
     * @param startDate the new start date of the subscription
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date of the subscription.
     *
     * @return the end date of the subscription
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the subscription.
     *
     * @param endDate the new end date of the subscription
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the user associated with the subscription.
     *
     * @return the user associated with the subscription
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with the subscription.
     *
     * @param user the new user associated with the subscription
     */
    public void setUser(User user) {
        this.user = user;
    }
}