package org.example.music_box_create_your_music_groupwork.service;

import org.example.music_box_create_your_music_groupwork.model.Role;
import org.example.music_box_create_your_music_groupwork.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing roles.
 * Provides methods for creating and finding roles.
 */
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    /**
     * Constructs a RoleService with the specified RoleRepository.
     *
     * @param roleRepository the RoleRepository to be used by this service
     */
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Creates a new role with the specified name.
     *
     * @param name the name of the role to be created
     * @return the created Role
     */
    public Role createRole(String name) {
        Role role = new Role(name);
        return roleRepository.save(role);
    }

    /**
     * Finds a role by its name.
     *
     * @param name the name of the role to be found
     * @return the found Role
     * @throws RuntimeException if no role is found with the specified name
     */
    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    // Other role-related methods can be added here, such as updating or deleting a role
}