package com.example.postservice.repository;

import com.example.postservice.entity.Post;
import com.example.postservice.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("TagRepository")
public interface TagRepository extends JpaRepository<Tag, Integer> {
    Optional<Tag> findById(int id);
}
