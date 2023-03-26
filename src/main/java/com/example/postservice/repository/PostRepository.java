package com.example.postservice.repository;

import com.example.postservice.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("PostRepository")
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(int id);
}
