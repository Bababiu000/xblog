package com.example.xblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.xblog.common.Result;
import com.example.xblog.dto.PagedQuery;
import com.example.xblog.entity.Category;
import com.example.xblog.enums.ResultType;
import com.example.xblog.exception.ServiceException;
import com.example.xblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 栏目表 前端控制器
 * </p>
 *
 * @author x
 * @since 2023-03-18
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // 分页查询
    @PostMapping("/list")
    public Result pagedQuery (@RequestBody PagedQuery pagedQuery) {
        Page<Category> page = new Page<>(pagedQuery.getPageNum(), pagedQuery.getPageSize());
        HashMap<String, Object> params = pagedQuery.getQueryParams();
        String title = (String) params.get("title");
        System.out.println(pagedQuery);

        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(title)){
            lambdaQueryWrapper.like(Category::getTitle, title);
        }
        lambdaQueryWrapper.orderByAsc(Category::getId);

        categoryService.page(page, lambdaQueryWrapper);
        return Result.success(page);
    }

    // 分页查询
    @GetMapping("/all")
    public Result pagedQuery () {
        List<Category> categoryList = categoryService.list();
        return Result.success(categoryList);
    }

    // 更新或添加
    @PostMapping("/save")
    public Result saveOrMod(@Validated @RequestBody Category category) {
        categoryService.saveOrUpdate(category);
        return Result.success();
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        try {
            categoryService.removeById(id);
        } catch (Exception e) {
            throw new ServiceException(ResultType.ERROR.getCode(), "删除失败！");
        }
        return Result.success();
    }

    // 批量删除
    @PostMapping("/delBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        try {
            categoryService.removeBatchByIds(ids);
        } catch (Exception e) {
            throw new ServiceException(ResultType.ERROR.getCode(), "删除失败！");
        }
        return Result.success();
    }
}
