package org.example.music_box_create_your_music_groupwork.repository;

import org.example.music_box_create_your_music_groupwork.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepo extends JpaRepository<PasswordResetToken, Long>{
    PasswordResetToken findByToken(String token);
}

