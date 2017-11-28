package com.showtime.blog.interceptor;


import com.showtime.blog.constant.AttributeNameConstant;
import com.showtime.blog.mapper.BloggerMapper;
import com.showtime.blog.mapper.LoginTicketMapper;
import com.showtime.blog.model.base.HostHolder;
import com.showtime.blog.model.entity.Blogger;
import com.showtime.blog.model.entity.LoginTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by nowcoder on 2016/7/3.
 */
@Component
public class PassportInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private BloggerMapper bloggerMapper;

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ticket = null;
        if (httpServletRequest.getCookies() != null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if (cookie.getName().equals("ticket")) {
                    ticket = cookie.getValue();
                    break;
                }
            }
        }

        if (ticket != null) {

            LoginTicket t1 = new LoginTicket();
            t1.setTicket(ticket);
            LoginTicket loginTicket = loginTicketMapper.selectOne(t1);

            if (loginTicket == null || loginTicket.getExpired().before(new Date()) || loginTicket.getStatus() == 0) {
                return true;
            }

            Blogger blogger = bloggerMapper.selectByPrimaryKey(1L);
            hostHolder.setBlogger(blogger);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && hostHolder.getBlogger() != null) {
            modelAndView.addObject(AttributeNameConstant.BLOGGER, hostHolder.getBlogger());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        hostHolder.clear();
    }
}
