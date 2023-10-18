package com.blogs.api.controller;

import com.blogs.api.SpringBootRestBloggingApiApplication;
import com.blogs.api.domains.dto.PostResponse;
import com.blogs.api.domains.dto.UserRequest;
import com.blogs.api.domains.dto.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.net.URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {SpringBootRestBloggingApiApplication.class}
)

public class UserControllerTest {

    @Autowired
    TestRestTemplate restTemplate;
    private final String BASE_URL = "/api/users";

    @Test
    void test_shouldReturn200() {
        ResponseEntity<UserResponse> response = restTemplate.getForEntity(BASE_URL + "/{userId}", UserResponse.class, 1);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    //get
    @Test
    void test_shouldReturnUserResponseWhenUserFound() {
        ResponseEntity<UserResponse> response = restTemplate.getForEntity((BASE_URL + "/{userId}"),
                UserResponse.class, 1);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        var userObject = response.getBody();
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, userObject.getId()),
                () -> Assertions.assertEquals("eneida", userObject.getName()),
                () -> Assertions.assertEquals("eneida", userObject.getEmail())
        );
    }


    //post
    @Test
    void test_shouldReturn201WhenUserIsCreated() {
        var userRequest = new UserRequest("eni", "eni.com", "111");
        ResponseEntity<Void> postResponse = restTemplate.postForEntity(BASE_URL, userRequest, Void.class);

        URI locationOfUser = postResponse.getHeaders().getLocation();

        ResponseEntity<UserResponse> getResponse = restTemplate.getForEntity(locationOfUser, UserResponse.class);
        var newlyCreatedUser = getResponse.getBody();

        Assertions.assertAll(
                () -> Assertions.assertEquals(HttpStatus.CREATED, postResponse.getStatusCode()),
                () -> Assertions.assertEquals(HttpStatus.OK, getResponse.getStatusCode()),
                () -> Assertions.assertEquals("eni", newlyCreatedUser.getName()),
                () -> Assertions.assertEquals("eni.com", newlyCreatedUser.getEmail())
        );
    }



    //put
    @Test
    void test_shouldReturn204WhenUserWithGivenIdIsUpdated() {
        //creating a user request with updated data
        var userRequest = new UserRequest("updated name", "updated email", "updated password");
        ResponseEntity<Void> putResponse = restTemplate.exchange(BASE_URL + "/{playerId}", HttpMethod.PUT,
                new HttpEntity<>(userRequest), Void.class, 2);
        //verify put operation api call is done w httpstatus.no_content
        Assertions.assertEquals(HttpStatus.NO_CONTENT, putResponse.getStatusCode());

        //verify that user with given id 2 is successfully updated
        ResponseEntity<UserResponse> getResponse = restTemplate.getForEntity(BASE_URL + "/{userId}", UserResponse.class, 2);

        var updatedUser = getResponse.getBody();
        Assertions.assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        Assertions.assertEquals("updated name", updatedUser.getName());
        Assertions.assertEquals("updated email", updatedUser.getEmail());
    }


    //get
    @Test
    public void test_shouldReturnAllPostsForAGivenUser() {
        ResponseEntity<String> getResponse = restTemplate
                .getForEntity(BASE_URL + "/{userId}/posts",
                        String.class, 1);
        Assertions.assertTrue((getResponse.getBody() != null));
    }


    //pika 6, get
    @Test
    public void test_shouldReturnPostForGivenUser() {
        ResponseEntity<PostResponse> getResponse = restTemplate.getForEntity(BASE_URL + "/{userId}/posts/{postId}", PostResponse.class, 1, 1);

        Assertions.assertAll(
                () -> Assertions.assertEquals(HttpStatus.OK, getResponse.getStatusCode()),
                () -> Assertions.assertEquals("post1", getResponse.getBody().getTitle()),
                () -> Assertions.assertEquals("body1", getResponse.getBody().getBody()),
                () -> Assertions.assertEquals("eneida", getResponse.getBody().getUser().getName())
        );
    }

}
