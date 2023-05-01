package com.example.postservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "userid", nullable = false, unique = true)
    private int userid;

    @Column(name = "title")
    private String title;

    @Column(name = "coverImage")
    private String coverImage;

    @Lob
    @Column(name = "content_text", columnDefinition = "TEXT")
    private String content_text;

    @Column(name = "content_img", columnDefinition = "TEXT")
    private String content_img;

    @Column(name = "tag")
    private String tag;

    @Column(name = "likeNum")
    private int likeNum=0;

    @Column(name = "unlikeNum")
    private int unlikeNum=0;

    @Column(name = "like_users")
    private String like_users="";

    @Column(name = "unlike_users")
    private String unlike_users="";

    @Column(name = "comment")
    private String comment;

    @Column(name = "url")
    private String url;

    @Column(name = "location")
    private String location;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
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

    public int getId() {
        return id;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public int getUnlikeNum() {
        return unlikeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public void setUnlikeNum(int unlikeNum) {
        this.unlikeNum = unlikeNum;
    }

    public String getComment() {
        return comment;
    }

    public String getLike_users() {
        return like_users;
    }

    public String getUnlike_users() {
        return unlike_users;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setLike_users(String like_users) {
        this.like_users = like_users;
    }

    public void setUnlike_users(String unlike_users) {
        this.unlike_users = unlike_users;
    }
}