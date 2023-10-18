package com.blogs.api.repository;

import com.blogs.api.domains.entity.CategoryEntity;
import com.blogs.api.domains.entity.PostEntity;
import com.blogs.api.domains.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    //all posts from a user with a certain id
    List<PostEntity> findAllPostsById(Integer userId);

    //all users whose name starts with a certain letter
    List<UserEntity> findByNameStartingWith(String firstLetter);

}

