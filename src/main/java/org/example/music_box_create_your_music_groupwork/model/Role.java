package org.example.music_box_create_your_music_groupwork.model;

import jakarta.persistence.*;

/**
 * Entity class representing a Role in the system.
 * This class is mapped to a database table using JPA annotations.
 */
@Entity
public class Role {

    /**
     * The unique identifier for the role.
     * This value is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the role.
     * This value must be unique and cannot be null.
     */
    @Column(nullable = false, unique = true)
    private String name;

    // Constructors

    /**
     * Default constructor for the Role class.
     */
    public Role() {
    }

    /**
     * Constructor for the Role class.
     *
     * @param name the name of the role
     */
    public Role(String name) {
        this.name = name;
    }

    // Getters and Setters

    /**
     * Gets the ID of the role.
     *
     * @return the ID of the role
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the role.
     *
     * @param id the new ID of the role
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the role.
     *
     * @return the name of the role
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the role.
     *
     * @param name the new name of the role
     */
    public void setName(String name) {
        this.name = name;
    }
}