package com.blogs.api.domains.dto;
import com.blogs.api.domains.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostResponse {
    private String title;
    private String body;
    private User user;

}
