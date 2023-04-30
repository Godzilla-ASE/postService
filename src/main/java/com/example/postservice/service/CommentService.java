package com.example.postservice.service;

import com.example.postservice.DTOMapping.dto.GetCommentDTO;
import com.example.postservice.DTOMapping.dto.GetReplyDTO;
import com.example.postservice.DTOMapping.dto.GetUserDTO;
import com.example.postservice.entity.Comment;
import com.example.postservice.entity.Post;
import com.example.postservice.entity.Reply;
import com.example.postservice.exceptions.ResourceNotFoundException;
import com.example.postservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private PostRepository postRepository;

    private RestTemplate template = new RestTemplate();


    private String URL="http://localhost:8080/users/";

    public Comment createComment(Comment comment){
        return commentRepository.save(comment);
    }
    public Reply createReply(Reply reply){
        Comment comment = commentRepository.findById(reply.getCommentid())
                .orElseThrow(() -> new ResourceNotFoundException("Comment not exist with id: " + reply.getCommentid()));
        comment.setReply(comment.getReply()+","+reply.getId());
        return replyRepository.save(reply);
    }

    public void deleteComment(int id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not exist with id: " + id));

        commentRepository.delete(comment);
    }

    public void deleteReply(int id){
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reply not exist with id: " + id));

        replyRepository.delete(reply);
    }

    public List<GetCommentDTO> getComments(int id) {
        List<Comment> comments = commentRepository.findAll();
        List<GetCommentDTO> result = new ArrayList<>();

        for (int i = 0; i < comments.size(); i++) {
            if(comments.get(i).getPostid()==id) {
                GetCommentDTO getCommentDTO = new GetCommentDTO();

                getCommentDTO.setId(comments.get(i).getId());
                getCommentDTO.setUserid(comments.get(i).getUserid());
                getCommentDTO.setUsername(getUserInfo(comments.get(i).getId()).getUsername());
                getCommentDTO.setCreation_date(comments.get(i).getCreation_date());
                getCommentDTO.setUser_avatar(getUserInfo(comments.get(i).getUserid()).getAvatarUrl());

                List<Reply> replies = replyRepository.findByCommentid(comments.get(i).getId());
                List<GetReplyDTO> getReplyDTO = new ArrayList<>();

                for (int j = 0; j < replies.size(); j++) {
                    GetReplyDTO g = new GetReplyDTO();
                    g.setUserid_from(replies.get(j).getUserid_from());
                    g.setUserid_to(replies.get(j).getUserid_to());
                    g.setUsername_from(getUserInfo(replies.get(j).getUserid_from()).getUsername());
                    g.setUsername_to(getUserInfo(replies.get(j).getUserid_from()).getUsername());
                    g.setContent(replies.get(j).getContent());
                    g.setCreation_date(replies.get(j).getCreation_date());
                    g.setUserAvatar_from(getUserInfo(replies.get(j).getUserid_from()).getAvatarUrl());
                    g.setUserAvatar_to(getUserInfo(replies.get(j).getUserid_to()).getAvatarUrl());

                    getReplyDTO.add(g);
                }

                getCommentDTO.setReply(getReplyDTO);

                result.add(getCommentDTO);
            }
        }

        return result;
    }

    public List<Reply> getReply(String replies) {
        if (replies != null) {
            String[] strArray = replies.split(",");
            List<Reply> replyList = new ArrayList<>();

            for (String s : strArray) {
                int replyid = Integer.parseInt(s);
                Reply reply = replyRepository.findById(replyid)
                        .orElseThrow(() -> new ResourceNotFoundException("Comment not exist with id: " + replyid));

                replyList.add(reply);
            }

            return replyList;
        }

        return null;
    }

    // ！！change url!
    public GetUserDTO getUserInfo(int id){
//        GetUserDTO getUserDTO = template.postForObject(URL + "" + id, null, GetUserDTO.class);
//        return getUserDTO;
        GetUserDTO getUserDTO = new GetUserDTO();
        getUserDTO.setAvatarUrl("image");
        getUserDTO.setUsername("user1");

        return getUserDTO;
    }

    public String getCommentById(int id){
        return commentRepository.findById(id).get().getContent();
    }
    public String getReplyById(int id){
        return replyRepository.findById(id).get().getContent();
    }

}