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

    @Column(name = "username", nullable = false)
    private String username;

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

    @Column(name = "like")
    private int like;

    @Column(name = "unlike")
    private int unlike;

//    @ElementCollection
//    @CollectionTable(name = "post_like")
//    @Column(name = "like", columnDefinition = "json")
//    private List<String> like;
//
//    @ElementCollection
//    @CollectionTable(name = "post_unlike")
//    @Column(name = "unlike", columnDefinition = "json")
//    private List<String> unlike;
//
//    @ElementCollection
//    @CollectionTable(name = "comment")
//    @Column(name = "comment", columnDefinition = "json")
//    private List<String> comment;

    @Column(name = "url")
    private String url;

    @Column(name = "location")
    private String location;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creation_date;
}