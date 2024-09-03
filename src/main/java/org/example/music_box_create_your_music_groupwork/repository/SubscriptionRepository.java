package org.example.music_box_create_your_music_groupwork.repository;

import org.example.music_box_create_your_music_groupwork.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;


public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {


    List<Subscription> findByUserId(Long userId); // find all subscriptions by user id
}