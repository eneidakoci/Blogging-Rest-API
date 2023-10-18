package com.blogs.api.service;

import com.blogs.api.domains.dto.PostResponse;
import com.blogs.api.domains.dto.UserRequest;
import com.blogs.api.domains.dto.UserResponse;
import com.blogs.api.domains.model.User;

import java.util.List;

//actions/methods that have to be implemented
public interface UserService {

    UserResponse getUserById(Integer id);
    User createUser(UserRequest request);
    UserResponse updateUser(Integer userId, UserRequest request);
    List<PostResponse> getUserPosts(Integer userId);
    //void addUserPosts()

    PostResponse getPostByUserId(Integer userId,Integer postId);

}
