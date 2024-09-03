package org.example.music_box_create_your_music_groupwork.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    // attributes
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();



    // empty constructor
    public Role(){
    }


    // constructor with role name
    public Role(String role) {
        this.name = role;
    }



    // getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String role) {
        this.name = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    // add a user
    public void addUser(User user){
        this.users.add(user);
        user.getRoles().add(this);
    }

    // remove a user
    public void removeUser(User user){
        this.users.remove(user);
        user.getRoles().remove(this);
    }

}