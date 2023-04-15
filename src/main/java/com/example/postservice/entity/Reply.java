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
@Table(name = "replies")
public class Reply {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "userid_from", nullable = false)
    private int userid_from;

    @Column(name = "userid_to", nullable = false)
    private int userid_to;

    @Column(name = "comment_id", nullable = false)
    private int commentid;

    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @GeneratedValue
    private Date creation_date;

    public int getId() {
        return id;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCommentid() {
        return commentid;
    }

    public int getUserid_from() {
        return userid_from;
    }

    public int getUserid_to() {
        return userid_to;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public void setUserid_from(int userid_from) {
        this.userid_from = userid_from;
    }

    public void setUserid_to(int userid_to) {
        this.userid_to = userid_to;
    }

}
