package com.showtime.blog.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.showtime.blog.constant.PostFieldConstant;
import com.showtime.blog.mapper.PostMapper;
import com.showtime.blog.model.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;

    /**
     * 通过类别查询文章信息
     * @param page
     * @param rows
     * @param post
     * @return
     */
    public Page<Post> getAll(Integer page, Integer rows, Post post){
        if (page != null && rows != null) {
            PageHelper.startPage(page, rows);
        }
        Example example = new Example(Post.class);
        Example.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(post.getCategoryId())) {
            criteria.andEqualTo(PostFieldConstant.CATEGORY_ID, post.getCategoryId());
        }
        return (Page<Post>) postMapper.selectByExample(example);
    }
}
