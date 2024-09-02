package org.example.music_box_create_your_music_groupwork.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "passwordresettoken")
public class PasswordResetToken {

    // attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "user_id", referencedColumnName = "id")
    private User user;

    @Column( nullable = false)
    private LocalDateTime expiryDateTime;


    // empty constructor
    public PasswordResetToken(){
    }

    // constructor
    public PasswordResetToken(Long id, String token, User user, LocalDateTime expiryDateTime) {
        this.id = id;
        this.token = token;
        this.user = user;
        this.expiryDateTime = expiryDateTime;
    }


    // constructor without id
    public PasswordResetToken(String token, User user, LocalDateTime expiryDateTime) {
        this.token = token;
        this.user = user;
        this.expiryDateTime = expiryDateTime;
    }


    // getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiryDateTime() {
        return expiryDateTime;
    }

    public void setExpiryDateTime(LocalDateTime expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }
}
