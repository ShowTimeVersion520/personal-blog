package com.showtime.blog.controller.admin;

import com.github.pagehelper.Page;
import com.showtime.blog.model.base.ViewObject;
import com.showtime.blog.model.entity.Categories;
import com.showtime.blog.model.entity.Post;
import com.showtime.blog.service.BloggerService;
import com.showtime.blog.service.CategoriesService;
import com.showtime.blog.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping(value = "/admin")
public class IndexController {

    @Autowired
    private PostService postService;

    @Autowired
    private BloggerService bloggerService;

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping(value = {"/index"})
    public String index(@RequestParam(value = "page",required = false, defaultValue = "1") Integer page,
                        @RequestParam(value = "rows",required = false, defaultValue = "10") Integer rows,
                        Model model) {
        List<ViewObject> vos = new ArrayList<>();
        Page<Post> posts = postService.getAll(page, rows, new Post());
        log.info("pageNum = {}, pages = {}, pageSize = {}", posts.getPageNum(), posts.getPages(), posts.getPageSize());
        List<Categories> categories = categoriesService.findCategoriesInCategoriesId(posts.getResult());
        for(Post post:posts){
            ViewObject vo = new ViewObject();
            vo.set("post", post);
            for(Categories category:categories){
                if(category.getId().equals(post.getCategoryId())){
                    vo.set("category", category.getName());
                }
            }
            vos.add(vo);
        }
        model.addAttribute("vos", vos);
        model.addAttribute("blogger", bloggerService.getBlogger());
        model.addAttribute("currentPage", page);
        model.addAttribute("size", rows);
        model.addAttribute("pageNum", posts.getPageNum());
        return "admin/index";
    }
}
