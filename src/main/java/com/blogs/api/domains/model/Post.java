package com.blogs.api.domains.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseDomain{
    private String title;
    private String body;
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post post)) return false;
        return Objects.equals(getTitle(), post.getTitle()) && Objects.equals(getBody(), post.getBody()) && Objects.equals(getUser(), post.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getBody(), getUser());
    }

    public Post(Integer id, String title, String body, User user) {
        super(id);
        this.title = title;
        this.body = body;
        this.user = user;
    }
}
