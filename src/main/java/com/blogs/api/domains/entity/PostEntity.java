package com.blogs.api.domains.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "post")
public class PostEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
    @ManyToMany
    @JoinTable(
            name = "post_category",
            joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private List<CategoryEntity> categories = new ArrayList<>();

    @Override
    public String toString() {
        return "PostEntity{" +
                "user=" + user +
                '}';
    }



}