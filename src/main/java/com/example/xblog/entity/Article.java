package com.example.xblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.xblog.entity.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author x
 * @since 2023-03-18
 */
@Data
@TableName("article")
@ApiModel(value = "Article对象", description = "文章表")
public class Article extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("文章内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("封面图")
    @TableField("picture")
    private String picture;

    @ApiModelProperty("所属栏目")
    @TableField("category_id")
    private Integer categoryId;

    @ApiModelProperty("发布用户")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("浏览量")
    @TableField("views")
    private Integer views;

}
