package com.example.xblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.xblog.common.Result;
import com.example.xblog.dto.PagedQuery;
import com.example.xblog.entity.Tag;
import com.example.xblog.enums.ResultType;
import com.example.xblog.exception.ServiceException;
import com.example.xblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    // 分页查询
    @PostMapping("/list")
    public Result pagedQuery (@RequestBody PagedQuery pagedQuery) {
        Page<Tag> page = new Page<>(pagedQuery.getPageNum(), pagedQuery.getPageSize());
        HashMap<String, Object> params = pagedQuery.getQueryParams();
        String name = (String) params.get("name");
        System.out.println(pagedQuery);

        LambdaQueryWrapper<Tag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(name)){
            lambdaQueryWrapper.like(Tag::getName, name);
        }
        lambdaQueryWrapper.orderByAsc(Tag::getId);

        tagService.page(page, lambdaQueryWrapper);
        return Result.success(page);
    }

    // 分页查询
    @GetMapping("/all")
    public Result pagedQuery () {
        List<Tag> tagList = tagService.list();
        return Result.success(tagList);
    }

    // 更新或添加
    @PostMapping("/save")
    public Result saveOrMod(@Validated @RequestBody Tag tag) {
        tagService.saveOrUpdate(tag);
        return Result.success();
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        try {
            tagService.removeById(id);
        } catch (Exception e) {
            throw new ServiceException(ResultType.ERROR.getCode(), "删除失败！");
        }
        return Result.success();
    }

    // 批量删除
    @PostMapping("/delBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        try {
            tagService.removeBatchByIds(ids);
        } catch (Exception e) {
            throw new ServiceException(ResultType.ERROR.getCode(), "删除失败！");
        }
        return Result.success();
    }
}
