package com.example.postservice.service;

import com.example.postservice.DTOMapping.dto.GetPostDTO;
import com.example.postservice.DTOMapping.dto.GetUserDTO;
import com.example.postservice.DTOMapping.dto.PutAttitudeDTO;
import com.example.postservice.entity.Post;
import com.example.postservice.exceptions.ResourceNotFoundException;
import com.example.postservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    RestTemplate restTemplate;

    private String URL="http://172.20.10.2:8080/users/";

    public Post createPost(Post post){
        post.setLikeNum(0);
        post.setUnlikeNum(0);
        post.setComment(null);
        post.setCreation_date(new Date());

        return postRepository.save(post);
    }

    public Post attitude(PutAttitudeDTO putAttitudeDTO){

        Post post = postRepository.findById(putAttitudeDTO.getPostid())
                .orElseThrow(() -> new ResourceNotFoundException("Post not exist with id:" + putAttitudeDTO.getPostid()));

        if(putAttitudeDTO.isAttitude_type()) {
            // cancel like
            if(putAttitudeDTO.getisCancel()){
                post.setLikeNum(post.getLikeNum() - 1);
                String sub = ""+putAttitudeDTO.getUserid();
                int index = post.getLike_users().indexOf(sub); // 查找子字符串的位置
                if (index != -1) { // 如果子字符串存在
                    post.setLike_users(post.getLike_users().substring(0, index) + post.getLike_users().substring(index + sub.length()+1)); // 删除子字符串
                }
            }else{
                post.setLikeNum(post.getLikeNum() + 1);
                post.setLike_users(post.getLike_users()+"" + putAttitudeDTO.getUserid() + ",");
            }
        }else{
            // cancel dislike
            if(putAttitudeDTO.getisCancel()){
                post.setUnlikeNum(post.getUnlikeNum() - 1);
                String sub = ""+putAttitudeDTO.getUserid();
                int index = post.getUnlike_users().indexOf(sub); // 查找子字符串的位置
                if (index != -1) { // 如果子字符串存在
                    post.setUnlike_users(post.getUnlike_users().substring(0, index) + post.getUnlike_users().substring(index + sub.length()+1)); // 删除子字符串
                }
            }else{
                post.setUnlikeNum(post.getUnlikeNum() + 1);
                post.setUnlike_users(post.getUnlike_users()+"" + putAttitudeDTO.getUserid() + ",");
            }
        }

        return postRepository.save(post);
    }


    public void deletePost(int id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not exist with id: " + id));

        postRepository.delete(post);
    }

    public Post getPostById(int id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not exist with id:" + id));

        return post;
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Post modifyPost(int id, Post newPost){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not exist with id:" + newPost.getId()));
        post.setTag(newPost.getTag());
        post.setLocation(newPost.getLocation());
        post.setContent_img(newPost.getContent_img());
        post.setContent_text(newPost.getContent_text());
        post.setCoverImage(newPost.getCoverImage());
        post.setTitle(newPost.getTitle());

        postRepository.save(post);

        return post;
    }

    public List<Post> getPostsByUsers(int userid){
        return postRepository.findByUserid(userid);
    }

    // ！！change url! -----------------------------------------------------------------
    public GetUserDTO getUserInfo(int id){
       GetUserDTO getUserDTO = restTemplate.getForObject("http://172.20.10.4:8080/users/" + id, GetUserDTO.class);
       return getUserDTO;
//        GetUserDTO getUserDTO = new GetUserDTO();
//        getUserDTO.setAvatarUrl("image");
//        getUserDTO.setUsername("user1");
//
//        return getUserDTO;
    }

    public List<GetUserDTO> getUsersByPost(int id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not exist with id:" + id));
        String ids = post.getLike_users();

        List<GetUserDTO> getUserDTOS = new ArrayList<>();

        System.out.println(ids.length());
        if(ids.length()>2) {
            List<String> result = Arrays.asList(ids.substring(0, ids.length() - 1).split(","));

            // System.out.println(result);
            for (String i : result) {
                // System.out.println(i);
                int userid = Integer.parseInt(i);
                getUserDTOS.add(getUserInfo(userid));
            }
        } else if (ids.length()==2) {
            int userid = Integer.parseInt(ids.substring(0, 1));
            getUserDTOS.add(getUserInfo(userid));
        }

        return getUserDTOS;
    }

    public List<Post> getPostsByTag(String tag){
        List<Post> posts = postRepository.findAll();
        List<Post> result = new ArrayList<>();

        for(Post post: posts){
            if(post.getTag()!=null) {
                int l = post.getTag().length();
                List<String> tags = Arrays.asList(post.getTag().substring(0, l - 2).split(","));

                for (String t : tags) {
                    if (t.equals(tag)) {
                        result.add(post);
                        break;
                    }
                }
            }
        }

        return result;
    }

    public List<Post> getPostsByLocation(String location){
        List<Post> posts = postRepository.findAll();
        List<Post> result = new ArrayList<>();

        for(Post post: posts){
            if (post.getLocation().equals(location)) {
                result.add(post);
            }
        }

        return result;
    }

    public int getUserid(int id){
        return postRepository.findById(id).get().getUserid();
    }
}