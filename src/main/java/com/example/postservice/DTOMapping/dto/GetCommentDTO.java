package com.example.postservice.DTOMapping.dto;

import com.example.postservice.entity.Reply;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Lob;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class GetCommentDTO {
    private int id;

    private int userid;

    private String username;

    private List<GetReplyDTO> reply;

    private String content;

    private Date creation_date;

    public Date getCreation_date() {
        return creation_date;
    }

    public String getContent() {
        return content;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<GetReplyDTO> getReply() {
        return reply;
    }

    public void setReply(List<GetReplyDTO> reply) {
        this.reply = reply;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getUserid() {
        return userid;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
