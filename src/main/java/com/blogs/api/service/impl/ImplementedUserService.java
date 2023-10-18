package com.blogs.api.service.impl;

import com.blogs.api.BloggingApiDataSource;
import com.blogs.api.domains.dto.PostResponse;
import com.blogs.api.domains.dto.UserRequest;
import com.blogs.api.domains.dto.UserResponse;
import com.blogs.api.domains.model.Post;
import com.blogs.api.domains.model.User;
import com.blogs.api.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ImplementedUserService implements UserService {
    private final BloggingApiDataSource dataSource;

    public ImplementedUserService(BloggingApiDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public UserResponse getUserById(Integer id) {
        var user = dataSource.getUsers().stream()
                .filter(u -> u.getId() == id)
                .findFirst().orElse(null);
        return user == null ? null : new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

    @Override
    public User createUser(UserRequest request) {
        var index = dataSource.getIdIndex();
        var user = new User(index, request.getName(), request.getEmail(), request.getPassword());
        return dataSource.getUsers().add(user) ? user : null;
    }

    @Override
    public UserResponse updateUser(Integer userId, UserRequest request) {
        var userToUpdate = this.dataSource.getUsers().stream()
                .filter(u -> u.getId() == userId)
                .findFirst().orElse(null);

        if (userToUpdate != null) {
            userToUpdate.setName(request.getName());
            userToUpdate.setEmail(request.getEmail());
            userToUpdate.setPassword(request.getPassword());
            this.dataSource.getUsers().add(userToUpdate);
        }

        return userToUpdate == null ? null : new UserResponse(userToUpdate.getId(),userToUpdate.getName(), userToUpdate.getEmail());
    }


    @Override
    public List<PostResponse> getUserPosts(Integer userId) {
        List<Post> postList = this.dataSource.getPosts().stream()
                .filter(up -> up.getUser().getId() == userId)
                .collect(Collectors.toList());

        List<PostResponse> postResponseList = postList.stream()
                .map(pr -> new PostResponse(pr.getTitle(), pr.getBody(), new User("eneida", "eneida","111")))
                .collect(Collectors.toList());
        return postResponseList;
    }

    @Override
    public PostResponse getPostByUserId(Integer userId, Integer postId){
        var postUser = this.dataSource.getPosts().stream()
                .filter(p -> p.getUser().getId() == userId)
                .filter(p -> p.getId() == postId)
                .findFirst()
                .orElse(null);
        return new PostResponse(postUser.getTitle(), postUser.getBody(), postUser.getUser());
    }
}
