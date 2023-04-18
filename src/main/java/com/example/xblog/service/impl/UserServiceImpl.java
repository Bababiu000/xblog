package com.example.xblog.service.impl;

import com.example.xblog.entity.User;
import com.example.xblog.mapper.UserMapper;
import com.example.xblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author x
 * @since 2023-03-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
