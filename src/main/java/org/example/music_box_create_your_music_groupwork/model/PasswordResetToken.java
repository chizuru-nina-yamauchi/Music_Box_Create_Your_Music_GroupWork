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

    private String token;

    private LocalDateTime expiryDateTime;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id", nullable = false)
    private User user;





    // empty constructor
    public PasswordResetToken(){
    }


    // constructor
    public PasswordResetToken(Long id, String token, LocalDateTime expiryDateTime, User user) {
        this.id = id;
        this.token = token;
        this.expiryDateTime = expiryDateTime;
        this.user = user;
    }


    // constructor without id
    public PasswordResetToken(String token, LocalDateTime expiryDateTime, User user) {
        this.token = token;
        this.expiryDateTime = expiryDateTime;
        this.user = user;
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

    public LocalDateTime getExpiryDateTime() {
        return expiryDateTime;
    }

    public void setExpiryDateTime(LocalDateTime expiryTime) {
        this.expiryDateTime = expiryTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
