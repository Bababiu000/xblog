package com.example.xblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.xblog.common.Result;
import com.example.xblog.dto.ArticleDTO;
import com.example.xblog.dto.PagedQuery;
import com.example.xblog.entity.Article;
import com.example.xblog.enums.ResultType;
import com.example.xblog.exception.ServiceException;
import com.example.xblog.service.ArticleService;
import com.example.xblog.service.CategoryService;
import com.example.xblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author x
 * @since 2023-03-18
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    // 分页查询
    @PostMapping("/list")
    public Result pagedQuery (@RequestBody PagedQuery pagedQuery) {
//        Page<Article> page = new Page<>(pagedQuery.getPageNum(), pagedQuery.getPageSize());
//        HashMap<String, Object> params = pagedQuery.getQueryParams();
//        String title = (String) params.get("title");
//
//        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        if(StringUtils.isNotBlank(title)){
//            lambdaQueryWrapper.like(Article::getTitle, title);
//        }
//        lambdaQueryWrapper.orderByAsc(Article::getId);

//        articleService.page(page, lambdaQueryWrapper);
        Page<ArticleDTO> articleList = articleService.list(pagedQuery);
        return Result.success(articleList);
    }

    // 更新或添加
    @PostMapping("/save")
    public Result saveOrMod(@Validated @RequestBody Article article) {
        articleService.saveOrUpdate(article);
        return Result.success(article.getId());
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        try {
            articleService.removeById(id);
        } catch (Exception e) {
            throw new ServiceException(ResultType.ERROR.getCode(), "删除失败！");
        }
        return Result.success();
    }

    // 批量删除
    @PostMapping("/delBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        try {
            articleService.removeBatchByIds(ids);
        } catch (Exception e) {
            throw new ServiceException(ResultType.ERROR.getCode(), "删除失败！");
        }
        return Result.success();
    }

    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable Integer id) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getId, id).last("limit 1");
        Article article = articleService.getOne(lambdaQueryWrapper);

        if(article == null)
            return Result.error("没有找到这条数据！");
        // 浏览量 +1
        article.setViews(article.getViews() + 1);
        articleService.updateById(article);
//        // 后端返回的对象
//        ArticleDTO articleDTO = new ArticleDTO();
//        // 复制 article 所有属性到 articleDTO
//        BeanUtils.copyProperties(article, articleDTO);
//        // 查询文章作者信息
//        if(article.getUserId() != null) {
//            User user =  userService.getById(article.getUserId());
//            if(user != null) {
//                articleDTO.setUsername(user.getUsername());
//            }
//        }
//        // 查询文章栏目信息
//        if(article.getCategoryId() != null) {
//            Category category =  categoryService.getById(article.getCategoryId());
//            if(category != null) {
//                articleDTO.setCategoryTitle(category.getTitle());
//            }
//        }
        ArticleDTO articleDTO = articleService.detail(id);
        return Result.success(articleDTO);
    }
}
