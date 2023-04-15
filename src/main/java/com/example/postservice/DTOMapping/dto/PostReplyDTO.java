package com.example.postservice.DTOMapping.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Lob;
import java.util.Date;

public class PostReplyDTO {
    private int userid_from;

    private int userid_to;

    private int commentid;

    private String content;

    private Date creation_date;

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public String getContent() {
        return content;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setUserid_to(int userid_to) {
        this.userid_to = userid_to;
    }

    public void setUserid_from(int userid_from) {
        this.userid_from = userid_from;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public int getUserid_to() {
        return userid_to;
    }

    public int getUserid_from() {
        return userid_from;
    }

    public int getCommentid() {
        return commentid;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
