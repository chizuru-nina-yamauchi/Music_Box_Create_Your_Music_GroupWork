package org.example.music_box_create_your_music_groupwork.service;

import org.example.music_box_create_your_music_groupwork.model.Role;
import org.example.music_box_create_your_music_groupwork.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service class for managing roles.
 * Provides methods for creating and finding roles.
 */
@Service
public class RoleService {

    private final RoleRepo roleRepo;
    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    /**
     * Creates a new role with the specified name.
     *
     * @param name the name of the role to be created
     * @return the created Role
     */
    public Role createRole(String name) {
        Role role = new Role(name);
        Role savedRole = roleRepo.save(role);
        logger.info("Created new role with name: {}", name);
        return savedRole;
    }

    /**
     * Finds a role by its name.
     *
     * @param name the name of the role to be found
     * @return the found Role
     * @throws IllegalArgumentException if no role is found with the specified name
     */
    public Role findByName(String name) {
        return roleRepo.findByName(name)
                .orElseThrow(() -> {
                    logger.error("Role not found with name: {}", name);
                    return new IllegalArgumentException("Role not found with name: " + name);
                });
    }

    /**
     * Updates the specified role.
     *
     * @param role the role to update
     * @return the updated role
     */
    public Role updateRole(Role role) {
        Role updatedRole = roleRepo.save(role);
        logger.info("Updated role with ID: {}", role.getId());
        return updatedRole;
    }

    /**
     * Deletes the role with the specified ID.
     *
     * @param id the ID of the role to delete
     */
    public void deleteRole(Long id) {
        roleRepo.deleteById(id);
        logger.info("Deleted role with ID: {}", id);
    }
}
