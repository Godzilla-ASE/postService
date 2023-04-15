package com.example.postservice.DTOMapping.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class PostPostDTO {
    private int userid;

    private String title;

    private String coverImage;

    private String content_text;

    private String content_img;

    private String tag;

    private String url;

    private String location;

    private Date creation_date;

    public int getUserid(){
        return userid;
    }
    public void setUserid(int userid){
        this.userid=userid;
    }

    public String getTitle(){return title;}
    public void setTitle(String title){
        this.title=title;
    }
    public String getCoverImage(){return coverImage;}
    public void setCoverImage(String coverImage){
        this.coverImage=coverImage;
    }
    public String getContent_text(){
        return content_text;
    }
    public void setContent_text(String content_text){
        this.content_text=content_text;
    }
    public void setContent_img(String content_img){
        this.content_img=content_img;
    }
    public String getContent_img(){
        return content_img;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
