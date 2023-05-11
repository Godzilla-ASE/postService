package com.example.postservice.controller;


import com.example.postservice.DTOMapping.Mapper;
import com.example.postservice.DTOMapping.dto.*;
import com.example.postservice.entity.Comment;
import com.example.postservice.entity.Reply;
import com.example.postservice.service.CommentService;
import com.example.postservice.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    private String URL="http://notify:8083/notification";
    private RestTemplate restTemplate = new RestTemplate();

    CommentController(CommentService commentService, PostService postService) {

        this.commentService = commentService;
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody PostCommentDTO comment) {
        // create Post
        Comment n = commentService.createComment(Mapper.INSTANCE.convertPostCommentDTOtoEntity(comment));
        SendUserInfo sendUserInfo = new SendUserInfo();
        sendUserInfo.setUserid_from(n.getUserid());
        sendUserInfo.setUserid_to(postService.getUserid(n.getPostid()));
        sendUserInfo.setType("COMMENT");
        sendUserInfo.setSend_to_client_id(n.getId());
        sendUserInfo.setSend_to_client(n.getContent());
        System.out.println("create send to user: "+sendUserInfo.getUserid_to());
        System.out.println(sendUserInfo.getSend_to_client());
        restTemplate.postForObject(URL, sendUserInfo, void.class);

        System.out.println("create comment successful");
        return ResponseEntity.ok(n);
    }

    @PostMapping("/reply")
    public ResponseEntity<Reply> createReply(@RequestBody PostReplyDTO reply) {
        // create Post

        Reply n = commentService.createReply(Mapper.INSTANCE.convertPostReplyDTOtoEntity(reply));
        SendUserInfo sendUserInfo = new SendUserInfo();
        sendUserInfo.setUserid_from(n.getUserid_from());
        sendUserInfo.setUserid_to(n.getUserid_to());
        sendUserInfo.setType("REPLY");
        sendUserInfo.setSend_to_client_id(n.getId());
        sendUserInfo.setSend_to_client(n.getContent());

        restTemplate.postForObject(URL, sendUserInfo, void.class);

        return ResponseEntity.ok(n);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable int id){
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/reply/{id}")
    public ResponseEntity<HttpStatus> deleteReply(@PathVariable int id){
        commentService.deleteReply(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<GetCommentDTO>> getComments(@PathVariable int id){
        return ResponseEntity.ok(commentService.getComments(id));
    }

    @GetMapping("/internal/{id}")
    public ResponseEntity<String> getCommentById(@PathVariable int id){
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @GetMapping("/reply/{id}")
    public ResponseEntity<String> getReplyById(@PathVariable int id){
        return ResponseEntity.ok(commentService.getReplyById(id));
    }

}
