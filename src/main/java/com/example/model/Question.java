package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by 강홍구 on 2016-11-24.
 */
@Entity
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, length = 20, nullable = false)
    private String writer;
    @Column(unique = true, length = 100, nullable = false)
    private String title;
    @Column(unique = true, length = 255, nullable = false)
    private String contents;

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
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

    @Override
    public String toString() {
        return "Question{" +
                "writer='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
