package com.example.model;

import javax.persistence.*;

/**
 * Created by 강홍구 on 2016-11-24.
 */

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, length = 20, nullable = false)
    private String userId;
    @Column(length = 20, nullable = false)
    private String password;
    @Column(length = 20, nullable = false)
    private String name;
    @Column(length = 30, nullable = true)
    private String email; // java reflection

    public void update (User user) {
        if (password.equals(user.password)) {
            this.name = user.name;
            this.email = user.email;
        }
    }

    public User() {
    }

    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // toString object에 있는 toString 이 기본

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
