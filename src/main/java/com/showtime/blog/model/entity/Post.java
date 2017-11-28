package com.showtime.blog.model.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Post {
    /**
     * 表主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 作者id号
     */
    @Column(name = "author_id")
    private Long authorId;

    /**
     * 标题
     */
    private String title;

    /**
     * 分类id号
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 添加时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 内容
     */
    private String content;

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
     * 获取作者id号
     *
     * @return author_id - 作者id号
     */
    public Long getAuthorId() {
        return authorId;
    }

    /**
     * 设置作者id号
     *
     * @param authorId 作者id号
     */
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取分类id号
     *
     * @return category_id - 分类id号
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * 设置分类id号
     *
     * @param categoryId 分类id号
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取添加时间
     *
     * @return create_time - 添加时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置添加时间
     *
     * @param createTime 添加时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}