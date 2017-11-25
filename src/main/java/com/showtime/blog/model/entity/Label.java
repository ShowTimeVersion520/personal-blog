package com.showtime.blog.model.entity;

import javax.persistence.*;

public class Label {
    /**
     * 表主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标签名称
     */
    private String name;

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
     * 获取标签名称
     *
     * @return name - 标签名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置标签名称
     *
     * @param name 标签名称
     */
    public void setName(String name) {
        this.name = name;
    }
}