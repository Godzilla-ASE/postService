package com.example.postservice.DTOMapping.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class GetPostDTO {
    private int id;
    private int userid;
    private String username;

    private String title;

    private String coverImage;

    private String content_text;

    private String content_img;

    private String tag;

    private int like;

    private int unlike;

    private List<GetCommentDTO> comment;

    private String url;

    private String location;

    private Date creation_date;
}
