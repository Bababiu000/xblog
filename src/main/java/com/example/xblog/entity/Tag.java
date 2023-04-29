package com.example.xblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
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
@TableName("tag")
@ApiModel(value = "Tag对象", description = "")
public class Tag extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("name")
    private String name;


}
