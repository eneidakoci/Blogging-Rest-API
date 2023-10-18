package com.blogs.api.controller;

import com.blogs.api.domains.dto.PostResponse;
import com.blogs.api.domains.dto.UserRequest;
import com.blogs.api.domains.dto.UserResponse;
import com.blogs.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer userId) {
        UserResponse userResponse = userService.getUserById(userId);
        return userResponse != null ? ResponseEntity.ok(userResponse) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserRequest userRequest, UriComponentsBuilder ucb) {
        var createdUser = userService.createUser(userRequest);
        URI locationOfCreatedUser = ucb
                .path("/api/users/{userId}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(locationOfCreatedUser).build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Integer userId, @RequestBody UserRequest userRequest) {
        UserResponse userResponse = this.userService.updateUser(userId, userRequest);

        return userResponse == null ? ResponseEntity.notFound().build() : ResponseEntity.noContent().build();
    }

    @GetMapping("{userId}/posts")
    public ResponseEntity<List<PostResponse>> getUserPosts(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.userService.getUserPosts(userId));
    }

    @GetMapping("/{userId}/posts/{postId}")
    public ResponseEntity<PostResponse> getPostByUserId(@PathVariable Integer userId, @PathVariable Integer postId) {
        return ResponseEntity.ok(this.userService.getPostByUserId(userId, postId));
    }
}
