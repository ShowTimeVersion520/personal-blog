package com.showtime.blog.service;

import com.showtime.blog.mapper.CategoriesMapper;
import com.showtime.blog.model.entity.Categories;
import com.showtime.blog.model.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesMapper categoriesMapper;

    public List<Categories> findCategoriesInCategoriesId(List<Post> posts){
        if(posts.size() == 0){
            return null;
        }
        return categoriesMapper.findCategoriesInCategoriesId(posts);
    }
}
