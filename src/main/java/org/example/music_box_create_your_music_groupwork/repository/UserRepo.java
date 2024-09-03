package org.example.music_box_create_your_music_groupwork.repository;

import org.example.music_box_create_your_music_groupwork.model.Role;
import org.example.music_box_create_your_music_groupwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    // find user by username
    User findByUsername(String username);


    // find by email
    User findByEmail(String email);


    // update user roles
    @Modifying
    @Query("UPDATE User u SET u.roles=?2 WHERE u.username=?1")
    void updateUserRoles(String username, Set<Role> roles);

}
