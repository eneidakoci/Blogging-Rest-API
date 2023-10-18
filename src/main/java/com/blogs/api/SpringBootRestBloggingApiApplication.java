package com.blogs.api;

import com.blogs.api.domains.entity.PostEntity;
import com.blogs.api.domains.entity.UserEntity;
import com.blogs.api.repository.PostRepository;
import com.blogs.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringBootRestBloggingApiApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestBloggingApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        testUserQuery();
    }

    public void testUserQuery() {
//		Integer firstUserId = 9;
//		List<PostEntity> posts = userRepository.findAllPostsById(firstUserId);
//		posts.forEach(System.err::println);
        List<UserEntity> usersWithJ = userRepository.findByNameStartingWith("j");//tostring fixed this
        usersWithJ.forEach(System.err::println);

        Integer userId = 9;
//        UserEntity user = userRepository.findById(userId).orElse(null); // Get the user with its id
//        if (user != null) {
//            List<PostEntity> posts = postRepository.findByUser(user);
//            posts.forEach(System.err::println);
//        }

//
        List<PostEntity> posts = postRepository.findAllByUserId(userId);
        posts.forEach(System.err::println);

    }
}