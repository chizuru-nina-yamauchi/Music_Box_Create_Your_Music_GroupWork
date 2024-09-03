package org.example.music_box_create_your_music_groupwork.repository;


import org.example.music_box_create_your_music_groupwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findByUsername(String username);


    User findByEmail(String email);



}
