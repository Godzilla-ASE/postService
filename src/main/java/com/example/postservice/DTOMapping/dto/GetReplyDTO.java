package com.example.postservice.DTOMapping.dto;

import java.util.Date;

public class GetReplyDTO {
    private int userid_from;

    private int userid_to;
    private String username_to;
    private String username_from;

    private String userAvatar_to;
    private String UserAvatar_from;

    public String getUserAvatar_from() {
        return UserAvatar_from;
    }

    public String getUserAvatar_to() {
        return userAvatar_to;
    }

    public void setUserAvatar_from(String userAvatar_from) {
        UserAvatar_from = userAvatar_from;
    }

    public void setUserAvatar_to(String userAvatar_to) {
        this.userAvatar_to = userAvatar_to;
    }

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


    public int getUserid_to() {
        return userid_to;
    }

    public int getUserid_from() {
        return userid_from;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername_from() {
        return username_from;
    }

    public String getUsername_to() {
        return username_to;
    }

    public void setUsername_from(String username_from) {
        this.username_from = username_from;
    }

    public void setUsername_to(String username_to) {
        this.username_to = username_to;
    }
}
