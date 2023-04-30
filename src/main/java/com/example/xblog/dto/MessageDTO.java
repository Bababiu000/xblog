package com.example.xblog.dto;

import com.example.xblog.entity.Message;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "MessageDTO对象", description = "MessageDTO对象")
public class MessageDTO extends Message {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("头像")
    private String avatarUrl;

    @ApiModelProperty("角色类型")
    private Integer roleType;
}
