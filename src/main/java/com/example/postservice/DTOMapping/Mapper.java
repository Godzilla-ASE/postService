package com.example.postservice.DTOMapping;
import com.example.postservice.DTOMapping.dto.*;
import com.example.postservice.entity.Comment;
import com.example.postservice.entity.Post;
import com.example.postservice.entity.Reply;
import org.mapstruct.factory.Mappers;
import org.mapstruct.*;

@org.mapstruct.Mapper
public interface Mapper {
    Mapper INSTANCE = Mappers.getMapper(Mapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userid", source="userid")
    @Mapping(target = "title", source="title")
    @Mapping(target = "coverImage", source="coverImage")
    @Mapping(target = "content_text", source="content_text")
    @Mapping(target = "content_img", source="content_img")
    @Mapping(target = "tag", source="tag")
    @Mapping(target = "url", source="url")
    @Mapping(target = "creation_date", source="creation_date")
    @Mapping(target = "location", source="location")
    @Mapping(target = "likeNum", ignore = true)
    @Mapping(target = "unlikeNum", ignore = true)
    @Mapping(target = "like_users", ignore = true)
    @Mapping(target = "unlike_users", ignore = true)
    @Mapping(target = "comment", ignore = true)
    Post convertPostPostDTOtoEntity(PostPostDTO postPostDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "userid", source="userid")
    @Mapping(target = "title", source="title")
    @Mapping(target = "coverImage", source="coverImage")
    @Mapping(target = "content_text", source="content_text")
    @Mapping(target = "content_img", source="content_img")
    @Mapping(target = "tag", source="tag")
    @Mapping(target = "url", source="url")
    @Mapping(target = "creation_date", source="creation_date")
    @Mapping(target = "location", source="location")
    @Mapping(target = "likeNum", source = "likeNum")
    @Mapping(target = "unlikeNum", source = "unlikeNum")
    @Mapping(target = "like_users", source = "like_users")
    @Mapping(target = "unlike_users", source = "like_users")
    @Mapping(target = "comment", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "user_avatar", ignore = true)
    GetPostDTO convertPostToGet(Post post);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userid", ignore = true)
    @Mapping(target = "title", source="title")
    @Mapping(target = "coverImage", source="coverImage")
    @Mapping(target = "content_text", source="content_text")
    @Mapping(target = "content_img", source="content_img")
    @Mapping(target = "tag", source="tag")
    @Mapping(target = "url", ignore = true)
    @Mapping(target = "creation_date", ignore = true)
    @Mapping(target = "location", source="location")
    @Mapping(target = "likeNum", ignore = true)
    @Mapping(target = "unlikeNum", ignore = true)
    @Mapping(target = "like_users", ignore = true)
    @Mapping(target = "unlike_users", ignore = true)
    @Mapping(target = "comment", ignore = true)
    Post convertPostPutDTOtoEntity(PostPutDTO postPutDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userid", source="userid")
    @Mapping(target = "content", source="content")
    @Mapping(target = "creation_date", source="creation_date")
    @Mapping(target = "reply", ignore = true)
    @Mapping(target = "postid", source="postid")
    Comment convertPostCommentDTOtoEntity(PostCommentDTO postCommentDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userid_to", source="userid_to")
    @Mapping(target = "userid_from", source="userid_from")
    @Mapping(target = "commentid", source="commentid")
    @Mapping(target = "content", source="content")
    @Mapping(target = "creation_date", source="creation_date")
    Reply convertPostReplyDTOtoEntity(PostReplyDTO postReplyDTO);

}
