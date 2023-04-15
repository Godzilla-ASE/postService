package com.example.postservice.service;

import com.example.postservice.DTOMapping.dto.GetCommentDTO;
import com.example.postservice.DTOMapping.dto.GetReplyDTO;
import com.example.postservice.entity.Comment;
import com.example.postservice.entity.Reply;
import com.example.postservice.exceptions.ResourceNotFoundException;
import com.example.postservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ReplyRepository replyRepository;

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

    public List<GetCommentDTO> getAllComments(int id) {
        List<Comment> comments = commentRepository.findAll();
        List<GetCommentDTO> result = new ArrayList<>();

        for (int i = 0; i < comments.size(); i++) {
            if(comments.get(i).getId()==id) {
                GetCommentDTO getCommentDTO = new GetCommentDTO();

                getCommentDTO.setId(comments.get(i).getId());
                getCommentDTO.setUserid(comments.get(i).getUserid());
                getCommentDTO.setContent(comments.get(i).getContent());
                getCommentDTO.setUsername("waiting...");
                getCommentDTO.setCreation_date(comments.get(i).getCreation_date());
                List<Reply> replies = replyRepository.findByCommentid(comments.get(i).getId());
                List<GetReplyDTO> getReplyDTO = new ArrayList<>();

                for (int j = 0; j < replies.size(); j++) {
                    GetReplyDTO g = new GetReplyDTO();
                    g.setUserid_from(replies.get(j).getUserid_from());
                    g.setUserid_to(replies.get(j).getUserid_to());
                    g.setUsername_from("waiting");
                    g.setUsername_to("waiting------");
                    g.setContent(replies.get(i).getContent());
                    g.setCreation_date(replies.get(j).getCreation_date());

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

}