package com.example.xblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2023-04-18
 */
@Getter
@Setter
@TableName("file_data")
@ApiModel(value = "FileData对象", description = "")
public class FileData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文件名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("文件类型")
    @TableField("type")
    private String type;

    @ApiModelProperty("文件大小(kb)")
    @TableField("size")
    private Long size;

    @ApiModelProperty("下载链接")
    @TableField("url")
    private String url;

    @ApiModelProperty("文件md5")
    @TableField("md5")
    private String md5;

    @ApiModelProperty("是否删除")
    @TableField("is_delete")
    private Boolean isDelete;


}
