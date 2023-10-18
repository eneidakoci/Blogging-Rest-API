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
public class PostCategory{
    private Post post;
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostCategory that)) return false;
        return Objects.equals(getPost(), that.getPost()) && Objects.equals(getCategory(), that.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPost(), getCategory());
    }
}
