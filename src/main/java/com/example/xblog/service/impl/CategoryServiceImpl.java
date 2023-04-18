package com.example.xblog.service.impl;

import com.example.xblog.entity.Category;
import com.example.xblog.mapper.CategoryMapper;
import com.example.xblog.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 栏目表 服务实现类
 * </p>
 *
 * @author x
 * @since 2023-03-18
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
