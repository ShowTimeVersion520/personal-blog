package com.showtime.blog.service;

import com.showtime.blog.constant.LoginTicketFieldConstant;
import com.showtime.blog.mapper.BloggerMapper;
import com.showtime.blog.mapper.LoginTicketMapper;
import com.showtime.blog.model.entity.Blogger;
import com.showtime.blog.model.entity.LoginTicket;
import com.showtime.blog.utils.BlogUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;



@Service
@Slf4j
public class BloggerService {
    @Autowired
    private BloggerMapper bloggerMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    public Map<String, Object> register(String username, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isBlank(username)) {
            map.put("msgname", "用户名不能为空");
            return map;
        }

        if (StringUtils.isBlank(password)) {
            map.put("msgpwd", "密码不能为空");
            return map;
        }

        Blogger b1 = new Blogger();
        b1.setAccount(username);
        Blogger blogger = bloggerMapper.selectOne(b1);

        if (blogger != null) {
            map.put("msgname", "用户名已经被注册");
            return map;
        }

        // 密码强度
        blogger = new Blogger();
        blogger.setAccount(username);
        blogger.setName(username);
        blogger.setSalt(UUID.randomUUID().toString().substring(0, 5));
        //默认头像
        String head = String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000));
        blogger.setHeadUrl(head);
        blogger.setPassword(BlogUtil.MD5(password+blogger.getSalt()));
        bloggerMapper.insert(blogger);
        // 登陆
        String ticket = addLoginTicket();
        map.put("ticket", ticket);
        return map;
    }


    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isBlank(username)) {
            map.put("msgname", "用户名不能为空");
            return map;
        }

        if (StringUtils.isBlank(password)) {
            map.put("msgpwd", "密码不能为空");
            return map;
        }

        Blogger b1 = new Blogger();
        b1.setAccount(username);
        Blogger blogger = bloggerMapper.selectOne(b1);

        if (blogger == null) {
            /** 如果博主第一次登录，则为其注册 */
            if(bloggerMapper.selectCount(new Blogger()) == 0){
                return this.register(username, password);
            }
            map.put("msgname", "用户名不存在");
            return map;
        }

        if (!BlogUtil.MD5(password+blogger.getSalt()).equals(blogger.getPassword())) {
            map.put("msgpwd", "密码不正确");
            return map;
        }

        map.put("userId", blogger.getId());

        String ticket = addLoginTicket();
        map.put("ticket", ticket);
        return map;
    }

    private String addLoginTicket() {
        LoginTicket ticket = new LoginTicket();
        Date date = new Date();
        date.setTime(date.getTime() + 1000*3600*24);
        //超时时间
        ticket.setExpired(date);
        ticket.setStatus((byte) 1);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicketMapper.insert(ticket);
        return ticket.getTicket();
    }

    public Blogger getBlogger() {
        return bloggerMapper.selectByPrimaryKey(1L);
    }

    public void logout(String ticket) {
        Example example = new Example(LoginTicket.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo(LoginTicketFieldConstant.TICKET, ticket);

        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setStatus((byte)0);
        loginTicketMapper.updateByExample(loginTicket, example);
    }
}
