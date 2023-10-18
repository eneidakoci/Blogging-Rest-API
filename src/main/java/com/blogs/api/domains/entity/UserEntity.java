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
@Table(name = "user")
public class UserEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<PostEntity> posts;

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                '}';
    }
}