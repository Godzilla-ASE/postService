package com.example.postservice.controller;


import com.example.postservice.DTOMapping.Mapper;
import com.example.postservice.DTOMapping.dto.GetCommentDTO;
import com.example.postservice.DTOMapping.dto.GetUserDTO;
import com.example.postservice.DTOMapping.dto.PostCommentDTO;
import com.example.postservice.DTOMapping.dto.PostReplyDTO;
import com.example.postservice.entity.Comment;
import com.example.postservice.entity.Reply;
import com.example.postservice.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody PostCommentDTO comment) {
        // create Post
        return ResponseEntity.ok(commentService.createComment(Mapper.INSTANCE.convertPostCommentDTOtoEntity(comment)));
    }

    @PostMapping("/reply")
    public ResponseEntity<Reply> createReply(@RequestBody PostReplyDTO reply) {
        // create Post
        return ResponseEntity.ok(commentService.createReply(Mapper.INSTANCE.convertPostReplyDTOtoEntity(reply)));
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
