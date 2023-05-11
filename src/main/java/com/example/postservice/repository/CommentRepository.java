package com.example.postservice.repository;

import com.example.postservice.entity.Comment;
import com.example.postservice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("CommentRepository")
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Optional<Comment> findById(int id);
    List<Comment> findByPostid(int postid);
}
