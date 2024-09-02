package org.example.music_box_create_your_music_groupwork.repository;

import org.example.music_box_create_your_music_groupwork.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VerificationTokenRepo extends JpaRepository<VerificationToken, Long> {

    // find by VerificationToken
    VerificationToken findByToken(String token);



    // find VerificationToken by username
    @Query("SELECT t FROM VerificationToken t WHERE t.user.username=?1")
    VerificationToken findByUsername(String username);

}
