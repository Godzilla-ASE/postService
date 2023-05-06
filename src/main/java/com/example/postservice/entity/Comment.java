package com.example.postservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "userid", nullable = false)
    private int userid;

    @Column(name = "postid", nullable = false)
    private int postid;

    @Column(name = "reply")
    private String reply="";

    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @GeneratedValue
    private Date creation_date;

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getPostid() {
        return postid;
    }

    public String getReply() {
        return reply;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

}
