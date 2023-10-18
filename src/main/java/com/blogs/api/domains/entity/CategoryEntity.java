
package com.blogs.api.domains.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {
    private String name;
    @ManyToMany(mappedBy = "categories")
    private List<PostEntity> posts = new ArrayList<>();
}