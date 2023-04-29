package com.example.xblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.xblog.entity.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author x
 * @since 2023-04-27
 */
@Getter
@Setter
@TableName("article_tag")
@ApiModel(value = "ArticleTag对象", description = "")
public class ArticleTag extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("article_id")
    private Integer articleId;

    @TableField("tag_id")
    private Integer tagId;


}
