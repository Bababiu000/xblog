package com.example.xblog.dto;

import com.example.xblog.entity.Article;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "ArticleDTO对象", description = "ArticleDTO对象")
public class ArticleDTO extends Article {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("栏目名称")
    private String categoryTitle;
}
