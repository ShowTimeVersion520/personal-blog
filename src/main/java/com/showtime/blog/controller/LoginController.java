package com.showtime.blog.controller;

import com.showtime.blog.constant.LoginTicketFieldConstant;
import com.showtime.blog.service.BloggerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private BloggerService bloggerService;

    @PostMapping(value = {"/login"})
    public String login(@Param("username") String username, @Param("password") String password,
                        Model model, HttpServletResponse response) {
        log.info("username: {}, password: {}", username, password);
        Map<String, Object> map = bloggerService.login(username, password);
        if(map.get("ticket") != null){
            Cookie cookie = new Cookie(LoginTicketFieldConstant.TICKET, map.get("ticket").toString());
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:admin/index";
        }
        model.addAllAttributes(map);
        return "login";
    }

    @GetMapping(value = {"/login"})
    public String index() {
        return "login";
    }

}
