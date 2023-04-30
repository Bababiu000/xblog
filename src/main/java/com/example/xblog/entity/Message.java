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
 * @since 2023-04-30
 */
@Getter
@Setter
@TableName("message")
@ApiModel(value = "Message对象", description = "")
public class Message extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("content")
    private String content;

    @TableField("user_id")
    private Integer userId;


}
