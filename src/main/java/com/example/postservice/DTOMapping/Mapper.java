package com.example.postservice.DTOMapping;
import com.example.postservice.DTOMapping.dto.PostCommentDTO;
import com.example.postservice.DTOMapping.dto.PostPostDTO;
import com.example.postservice.DTOMapping.dto.PostReplyDTO;
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
    @Mapping(target = "like", ignore = true)
    @Mapping(target = "unlike", ignore = true)
    @Mapping(target = "like_users", ignore = true)
    @Mapping(target = "unlike_users", ignore = true)
    @Mapping(target = "comment", ignore = true)
    Post convertPostPostDTOtoEntity(PostPostDTO postPostDTO);

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
