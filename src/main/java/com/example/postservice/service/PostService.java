package com.example.postservice.service;

import com.example.postservice.DTOMapping.dto.PutAttitudeDTO;
import com.example.postservice.entity.Post;
import com.example.postservice.exceptions.ResourceNotFoundException;
import com.example.postservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post){
        post.setLike(0);
        post.setUnlike(0);
        post.setCreation_date(new Date());
        post.setComment(null);

        return postRepository.save(post);
    }

    public Post attitude(PutAttitudeDTO putAttitudeDTO){

        Post post = postRepository.findById(putAttitudeDTO.getPostid())
                .orElseThrow(() -> new ResourceNotFoundException("Post not exist with id:" + putAttitudeDTO.getPostid()));

        if(putAttitudeDTO.isAttitude_type()) {
            // cancel like
            if(putAttitudeDTO.isCancel()){
                post.setLike(post.getLike() - 1);
                String sub = ""+putAttitudeDTO.getUserid();
                int index = post.getLike_users().indexOf(sub); // 查找子字符串的位置
                if (index != -1) { // 如果子字符串存在
                    post.setLike_users(post.getLike_users().substring(0, index) + post.getLike_users().substring(index + sub.length()+1)); // 删除子字符串
                }
            }else{
                post.setLike(post.getLike() + 1);
                post.setLike_users("" + putAttitudeDTO.getUserid() + ",");
            }
        }else{
            // cancel dislike
            if(putAttitudeDTO.isCancel()){
                post.setLike(post.getUnlike() - 1);
                String sub = ""+putAttitudeDTO.getUserid();
                int index = post.getUnlike_users().indexOf(sub); // 查找子字符串的位置
                if (index != -1) { // 如果子字符串存在
                    post.setLike_users(post.getUnlike_users().substring(0, index) + post.getUnlike_users().substring(index + sub.length()+1)); // 删除子字符串
                }
            }else{
                post.setUnlike(post.getUnlike() + 1);
                post.setUnlike_users("" + putAttitudeDTO.getUserid() + ",");
            }
        }

        return postRepository.save(post);
    }

    public Post unlike(int id, int userid){

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not exist with id:" + id));
        post.setUnlike(post.getUnlike()+1);
        post.setUnlike_users(""+userid+",");

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

    public Post modifyPost(Post newPost){
        Post post = postRepository.findById(newPost.getId())
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

}