package com.example.xblog.mapper;

import com.example.xblog.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author x
 * @since 2023-03-18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
