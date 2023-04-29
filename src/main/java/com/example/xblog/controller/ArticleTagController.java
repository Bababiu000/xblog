package com.example.xblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.xblog.common.Result;
import com.example.xblog.entity.ArticleTag;
import com.example.xblog.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author x
 * @since 2023-04-27
 */
@RestController
@RequestMapping("/article-tag")
public class ArticleTagController {
    @Autowired
    ArticleTagService articleTagService;

    @GetMapping("/{articleId}")
    public Result currentTag(@PathVariable Integer articleId) {
        LambdaQueryWrapper<ArticleTag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ArticleTag::getArticleId, articleId);
        lambdaQueryWrapper.select(ArticleTag::getTagId);
        List<ArticleTag> articleTagList = articleTagService.list(lambdaQueryWrapper);
        List<Integer> list = new ArrayList<>();
        for (ArticleTag articleTag : articleTagList) {
            list.add(articleTag.getTagId());
        }
        return Result.success(list);
    }

    @PostMapping("/save")
    public Result save(@RequestBody List<ArticleTag> articleTagList) {
        for(ArticleTag articleTag: articleTagList) {
            LambdaQueryWrapper<ArticleTag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ArticleTag::getArticleId, articleTag.getArticleId());
            lambdaQueryWrapper.eq(ArticleTag::getTagId, articleTag.getTagId());
            if(articleTagService.count(lambdaQueryWrapper) > 0) {
                continue;
            } else {
                articleTagService.save(articleTag);
            }
        }
        return Result.success();
    }
}
