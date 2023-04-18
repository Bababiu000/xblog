package com.example.xblog.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.xblog.dto.ArticleDTO;
import com.example.xblog.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author x
 * @since 2023-03-18
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    ArticleDTO detail(@Param("id") Integer id);

    Page<ArticleDTO> list(Page<Article> page, @Param("params") HashMap<String, Object> params);
}
