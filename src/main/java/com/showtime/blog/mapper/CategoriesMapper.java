package com.showtime.blog.mapper;

import com.showtime.blog.model.entity.Categories;
import com.showtime.blog.model.entity.Post;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CategoriesMapper extends Mapper<Categories> {

    List<Categories> findCategoriesInCategoriesId(List<Post> posts);
}