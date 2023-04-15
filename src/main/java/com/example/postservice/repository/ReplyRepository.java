package com.example.postservice.repository;

import com.example.postservice.entity.Post;
import com.example.postservice.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("ReplyRepository")
public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    Optional<Reply> findById(int id);

    List<Reply> findByCommentid(int id);
}
