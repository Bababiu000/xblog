package com.example.xblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author x
 * @since 2023-04-16
 */
@Getter
@Setter
@TableName("menu")
@ApiModel(value = "Menu对象", description = "菜单表")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("路径")
    @TableField("path")
    private String path;

    @ApiModelProperty("图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty("描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("权限级别，0代表普通用户，1代表管理员，逗号分隔")
    @TableField("menu_right")
    private String menuRight;

    @ApiModelProperty("排序")
    @TableField("sort_num")
    private Integer sortNum;

    @ApiModelProperty("页面路径")
    @TableField("page_path")
    private String pagePath;
}
