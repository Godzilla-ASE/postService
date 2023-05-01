package com.example.postservice.controller;

import com.example.postservice.DTOMapping.dto.*;
import com.example.postservice.DTOMapping.Mapper;
import com.example.postservice.entity.Post;
import com.example.postservice.service.CommentService;
import com.example.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final RestTemplate restTemplate = new RestTemplate();
    private String URL="http://localhost:9001/users";

    PostController(PostService postService) {

        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createPost(@RequestBody PostPostDTO post) {
        // create Post
        // return ResponseEntity.ok(postService.createPost(Mapper.INSTANCE.convertPostPostDTOtoEntity(post)));
        // System.out.println("created!");
        postService.createPost(Mapper.INSTANCE.convertPostPostDTOtoEntity(post));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @PutMapping("/attitude")
//    public ResponseEntity<HttpStatus> attitude(@RequestBody PutAttitudeDTO putAttitudeDTO) {
//        // create Post
//        //return ResponseEntity.ok(postService.attitude(putAttitudeDTO));
//        postService.attitude(putAttitudeDTO);
//        if(putAttitudeDTO.isAttitude_type()==true && putAttitudeDTO.getisCancel()==false){
//            SendUserInfo sendUserInfo = new SendUserInfo();
//            sendUserInfo.setUserid_from(putAttitudeDTO.getUserid());
//            sendUserInfo.setUserid_to(postService.getUserid(putAttitudeDTO.getPostid()));
//            sendUserInfo.setType("LIKE_POST");
//            sendUserInfo.setSend_to_client_id(putAttitudeDTO.getPostid());
//            sendUserInfo.setSend_to_client(postService.getPostById(putAttitudeDTO.getPostid()).getContent_text());
//
//            restTemplate.postForObject(URL, sendUserInfo, null);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
@PutMapping("/attitude")
public ResponseEntity<String> attitude(@RequestBody PutAttitudeDTO putAttitudeDTO) {
    // create Post
    //return ResponseEntity.ok(postService.attitude(putAttitudeDTO));
    //System.out.println("send success");
    postService.attitude(putAttitudeDTO);
    if(putAttitudeDTO.isAttitude_type()==true && putAttitudeDTO.getisCancel()==false){
        SendUserInfo sendUserInfo = new SendUserInfo();
        sendUserInfo.setUserid_from(putAttitudeDTO.getUserid());
        sendUserInfo.setUserid_to(postService.getUserid(putAttitudeDTO.getPostid()));
        sendUserInfo.setType("LIKE_POST");
        sendUserInfo.setSend_to_client_id(putAttitudeDTO.getPostid());
        sendUserInfo.setSend_to_client(postService.getPostById(putAttitudeDTO.getPostid()).getContent_text());

        //restTemplate.postForObject(URL, sendUserInfo, null);
        //System.out.println("send success");
    }
    return ResponseEntity.ok("success");
}

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable int id){
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetPostDTO> getPostById(@PathVariable int id){
        GetPostDTO getPostDTO = Mapper.INSTANCE.convertPostToGet(postService.getPostById(id));
        getPostDTO.setUsername(postService.getUserInfo(getPostDTO.getUserid()).getUsername());
        getPostDTO.setUser_avatar(postService.getUserInfo(getPostDTO.getUserid()).getAvatarUrl());

        return ResponseEntity.ok(getPostDTO);
    }

    @GetMapping
    public ResponseEntity<List<GetPostDTO>> getAllPosts(){
        //System.out.println("send success");
        //restTemplate.postForObject(URL, null, null);

        List<GetPostDTO> getPostDTOS = new ArrayList<>();
        List<Post> posts = postService.getAllPosts();
        for(Post p:posts){
            GetPostDTO getPostDTO = Mapper.INSTANCE.convertPostToGet(p);
            getPostDTO.setUsername(postService.getUserInfo(p.getUserid()).getUsername());
            getPostDTO.setUser_avatar(postService.getUserInfo(getPostDTO.getUserid()).getAvatarUrl());
            getPostDTOS.add(getPostDTO);

            //System.out.println("get post:"+ getPostDTO.getTitle());
        }
        return ResponseEntity.ok(getPostDTOS);
    }


    @PutMapping("{id}")
    public ResponseEntity<Post> ModifyPost(@RequestBody PostPutDTO postPutDTO, @PathVariable int id) {
        // create Post
        return ResponseEntity.ok(postService.modifyPost(id,Mapper.INSTANCE.convertPostPutDTOtoEntity(postPutDTO)));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<GetPostDTO>> getPostsByUsers(@PathVariable int id){
        List<GetPostDTO> getPostDTOS = new ArrayList<>();
        List<Post> posts = postService.getPostsByUsers(id);
        for(Post p:posts){
            GetPostDTO getPostDTO = Mapper.INSTANCE.convertPostToGet(p);
            getPostDTO.setUsername(postService.getUserInfo(p.getUserid()).getUsername());
            getPostDTO.setUser_avatar(postService.getUserInfo(getPostDTO.getUserid()).getAvatarUrl());
            getPostDTOS.add(getPostDTO);
        }

        return ResponseEntity.ok(getPostDTOS);
    }

    @GetMapping("/likedUsers/{id}")
    public ResponseEntity<List<GetUserDTO>> getUsersByPostid(@PathVariable int id){
        return ResponseEntity.ok(postService.getUsersByPost(id));
    }

    @GetMapping("/tags/{tag}")
    public ResponseEntity<List<GetPostDTO>> getPostsByTag(@PathVariable String tag){
        List<GetPostDTO> getPostDTOS = new ArrayList<>();
        List<Post> posts = postService.getPostsByTag(tag);
        for(Post p:posts){
            GetPostDTO getPostDTO = Mapper.INSTANCE.convertPostToGet(p);
            getPostDTO.setUsername(postService.getUserInfo(p.getUserid()).getUsername());
            getPostDTO.setUser_avatar(postService.getUserInfo(getPostDTO.getUserid()).getAvatarUrl());
            getPostDTOS.add(getPostDTO);
        }

        return ResponseEntity.ok(getPostDTOS);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<GetPostDTO>> getPostsByLocation(@PathVariable String location){
        List<GetPostDTO> getPostDTOS = new ArrayList<>();
        List<Post> posts = postService.getPostsByLocation(location);
        for(Post p:posts){
            GetPostDTO getPostDTO = Mapper.INSTANCE.convertPostToGet(p);
            getPostDTO.setUsername(postService.getUserInfo(p.getUserid()).getUsername());
            getPostDTO.setUser_avatar(postService.getUserInfo(getPostDTO.getUserid()).getAvatarUrl());
            getPostDTOS.add(getPostDTO);
        }

        return ResponseEntity.ok(getPostDTOS);
    }

}
