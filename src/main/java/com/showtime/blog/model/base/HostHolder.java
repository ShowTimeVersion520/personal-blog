package com.showtime.blog.model.base;

import com.showtime.blog.model.entity.Blogger;
import org.springframework.stereotype.Component;

@Component
public class HostHolder {
    private static ThreadLocal<Blogger> bloggers = new ThreadLocal<Blogger>();

    public Blogger getBlogger() {
        return bloggers.get();
    }

    public void setBlogger(Blogger blogger) {
        bloggers.set(blogger);
    }

    public void clear() {
        bloggers.remove();
    }
}
