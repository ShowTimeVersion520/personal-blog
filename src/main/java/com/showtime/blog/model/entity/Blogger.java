package com.showtime.blog.model.entity;

import javax.persistence.*;

public class Blogger {
    /**
     * 表主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 头像
     */
    @Column(name = "head_url")
    private String headUrl;

    /**
     * 昵称
     */
    private String name;

    /**
     * 介绍
     */
    private String introduce;

    /**
     * 获取表主键
     *
     * @return id - 表主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置表主键
     *
     * @param id 表主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取账号
     *
     * @return account - 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账号
     *
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取盐
     *
     * @return salt - 盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置盐
     *
     * @param salt 盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取头像
     *
     * @return head_url - 头像
     */
    public String getHeadUrl() {
        return headUrl;
    }

    /**
     * 设置头像
     *
     * @param headUrl 头像
     */
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    /**
     * 获取昵称
     *
     * @return name - 昵称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置昵称
     *
     * @param name 昵称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取介绍
     *
     * @return introduce - 介绍
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 设置介绍
     *
     * @param introduce 介绍
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}