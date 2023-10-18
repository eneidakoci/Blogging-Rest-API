package com.blogs.api;

import com.blogs.api.domains.model.Category;
import com.blogs.api.domains.model.Post;
import com.blogs.api.domains.model.PostCategory;
import com.blogs.api.domains.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class BloggingApiDataSource {

        public static Set<User> users = new HashSet<>();
        public static Set<PostCategory> postCategories= new HashSet<>();
        public static Set<Post> posts = new HashSet<>();
        public static Set<Category> categories= new HashSet<>();
        public static Integer idIndex = 1;

        @PostConstruct
        public void init(){
            this.initBloggingDataFromCsv();
        }


        public void initBloggingDataFromCsv(){
            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(new ClassPathResource("bloggingExc.csv").getFile()))) {
                String headerLine = bufferedReader.readLine();
                var line = "";

                while ((line=bufferedReader.readLine())!=null){
                    var data = line.split(",");

                    //set user
                    var user = new User(Integer.valueOf(data[0]),
                            data[3], data[4],data[5]);
                    users.add(user);

                    //set post
                    var post = new Post(Integer.valueOf(data[0]),data[1],data[2], user);
                    posts.add(post);

                    //set category
                    var category = new Category(data[6]);
                    categories.add(category);

                    //set postCategory
                    var postCategory = new PostCategory(post,category);
                    postCategories.add(postCategory);

                    idIndex++;
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Set<User> getUsers() {
            return users;
        }

        public Set<Post> getPosts() {
            return posts;
        }

        public Set<Category> getCategory() {
            return categories;
        }

        public Set<PostCategory> getPostCategories() {
            return postCategories;
        }

        public Integer getIdIndex() {
            return idIndex;
        }
    }

