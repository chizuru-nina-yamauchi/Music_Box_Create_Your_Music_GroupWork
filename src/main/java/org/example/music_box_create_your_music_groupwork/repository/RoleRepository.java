package org.example.music_box_create_your_music_groupwork.repository;

import org.example.music_box_create_your_music_groupwork.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository interface for managing Role entities.
 * Extends JpaRepository to provide CRUD operations and additional query methods.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds a Role by its name.
     *
     * @param name the name of the role to find
     * @return an Optional containing the found Role, or an empty Optional if no Role is found
     */
    Optional<Role> findByName(String name);
}