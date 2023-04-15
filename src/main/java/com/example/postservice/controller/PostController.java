package com.example.postservice.controller;

import com.example.postservice.DTOMapping.dto.PostPostDTO;
import com.example.postservice.DTOMapping.Mapper;
import com.example.postservice.DTOMapping.dto.PutAttitudeDTO;
import com.example.postservice.entity.Post;
import com.example.postservice.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostPostDTO post) {
        // create Post
        return ResponseEntity.ok(postService.createPost(Mapper.INSTANCE.convertPostPostDTOtoEntity(post)));
    }

    @PutMapping("/attitude")
    public ResponseEntity<Post> attitude(@RequestBody PutAttitudeDTO putAttitudeDTO) {
        // create Post
        return ResponseEntity.ok(postService.attitude(putAttitudeDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable int id){
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getPostById(@PathVariable int id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }


    @PutMapping
    public ResponseEntity<Post> ModifyPost(@RequestBody Post post) {
        // create Post
        return ResponseEntity.ok(postService.modifyPost(post));
    }

}
