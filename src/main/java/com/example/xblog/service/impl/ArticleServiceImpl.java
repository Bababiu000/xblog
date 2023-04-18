package com.example.xblog.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xblog.dto.ArticleDTO;
import com.example.xblog.dto.PagedQuery;
import com.example.xblog.entity.Article;
import com.example.xblog.mapper.ArticleMapper;
import com.example.xblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author x
 * @since 2023-03-18
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public ArticleDTO detail(Integer id) {
        return articleMapper.detail(id);
    }

    @Override
    public Page<ArticleDTO> list(PagedQuery pagedQuery) {
        Page<Article> page = new Page<>(pagedQuery.getPageNum(), pagedQuery.getPageSize());
        HashMap<String, Object> params = pagedQuery.getQueryParams();
        return articleMapper.list(page, params);
    }
}
