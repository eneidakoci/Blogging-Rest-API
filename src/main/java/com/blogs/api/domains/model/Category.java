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
public class Category extends BaseDomain{
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return Objects.equals(getName(), category.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public Category(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
