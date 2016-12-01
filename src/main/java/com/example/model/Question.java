package com.example.model;

import javax.persistence.*;

/**
 * Created by 강홍구 on 2016-11-24.
 */
@Entity
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User user;

    @Column(unique = true, length = 100, nullable = false)
    private String title;

    @Column(unique = true, length = 255, nullable = false)
    private String contents;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

}
