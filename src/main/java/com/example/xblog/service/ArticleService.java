package com.example.xblog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.xblog.dto.ArticleDTO;
import com.example.xblog.dto.PagedQuery;
import com.example.xblog.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author x
 * @since 2023-03-18
 */
public interface ArticleService extends IService<Article> {

    ArticleDTO detail(Integer id);

    Page<ArticleDTO> list (PagedQuery pageQuery);
}
