package com.bookstore.bookexplorer.service;

import com.bookstore.bookexplorer.model.Post;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class GetPostsService {
    public static List<Post> getPosts() {
        RestTemplate restTemplate = new RestTemplate();
        Post[] posts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", Post[].class);
        return Arrays.asList(posts);
    }
}
