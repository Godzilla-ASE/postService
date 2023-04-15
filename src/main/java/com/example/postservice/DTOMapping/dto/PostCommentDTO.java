package com.example.postservice.DTOMapping.dto;

import com.example.postservice.entity.Reply;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PostCommentDTO {
    private int userid;
    private int postid;

    private String content;

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

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getPostid() {
        return postid;
    }
}
