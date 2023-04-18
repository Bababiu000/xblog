package com.example.xblog.mapper;

import com.example.xblog.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 栏目表 Mapper 接口
 * </p>
 *
 * @author x
 * @since 2023-03-18
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
