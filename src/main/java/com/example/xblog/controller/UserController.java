package com.example.xblog.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.xblog.common.Result;
import com.example.xblog.dto.PagedQuery;
import com.example.xblog.entity.Menu;
import com.example.xblog.entity.User;
import com.example.xblog.enums.ResultType;
import com.example.xblog.enums.RoleType;
import com.example.xblog.exception.ServiceException;
import com.example.xblog.service.MenuService;
import com.example.xblog.service.UserService;
import com.example.xblog.utils.JwtUtils;
import com.example.xblog.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author x
 * @since 2023-03-18
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    // 分页查询
    @PostMapping("/list")
    public Result pagedQuery (@RequestBody PagedQuery pagedQuery) {
        Page<User> page = new Page<>(pagedQuery.getPageNum(), pagedQuery.getPageSize());
        HashMap<String, Object> param = pagedQuery.getQueryParams();
        String username = (String) param.get("username");
        String email = (String) param.get("email");
        String address = (String) param.get("address");

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            lambdaQueryWrapper.like(User::getUsername, username);
        }
        if(StringUtils.isNotBlank(email)){
            lambdaQueryWrapper.like(User::getEmail, email);
        }
        if(StringUtils.isNotBlank(address)){
            lambdaQueryWrapper.like(User::getAddress, address);
        }
        lambdaQueryWrapper.orderByAsc(User::getId);

        userService.page(page, lambdaQueryWrapper);
        return Result.success(page);
    }

    // 根据用户名查询
    @GetMapping("/{username}")
    public Result delete(@PathVariable String username) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, username)
                .last("limit 1");
        User curUser = userService.getOne(lambdaQueryWrapper);
        if(curUser != null)
            return Result.error("该用户名已被使用！");
        return Result.success();
    }

    // 更新或添加
    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user) {

        Integer userId = JwtUtils.getCurrentUserInfo();
        if (userId != null) {

            //普通用户
            if (RoleType.ROLE_USER.getCode().equals(user.getRoleType())) {
                if (!user.getId().equals(userId)) {
                    throw new ServiceException("用户ID不相等，请重新登录后尝试");
                }
            }
            User userInfo = userService.getById(userId);

            if(userInfo != null) {
                //超级管理员
                if (RoleType.ROLE_ADMIN.getCode().equals(user.getRoleType()) && user.getRoleType().equals(userInfo.getRoleType())) {
                    userService.saveOrUpdate(user);
                }else{
                    throw new ServiceException("用户权限与数据库不一致");
                }
            }else {
                throw new ServiceException("用户未找到");
            }

            return Result.success();
        }
        return Result.error();
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        try {
            userService.removeById(id);
        } catch (Exception e) {
            throw new ServiceException(ResultType.ERROR.getCode(), "删除失败！");
        }
        return Result.success();
    }

    // 批量删除
    @PostMapping("/delBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        try {
            userService.removeBatchByIds(ids);
        } catch (Exception e) {
            throw new ServiceException(ResultType.ERROR.getCode(), "删除失败！");
        }
        return Result.success();
    }

    // 登录
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername())
                .last("limit 1");
        User curUser = userService.getOne(lambdaQueryWrapper);
        if(curUser == null || !curUser.getPassword().equals(SecureUtil.md5(user.getPassword() + curUser.getSalt())))
            return Result.error("用户名或密码错误");
        HashMap<String, Object> res = new HashMap<>();
        List<Menu> menuList = menuService.query().
                like("menu_right", curUser.getRoleType())
                .orderByAsc("sort_num")
                .list();
        String token = JwtUtils.generateToken(curUser);
        res.put("user", curUser);
        res.put("menuList", menuList);
        res.put("token", token);
        return Result.success("登录成功！", res);
    }

    @PostMapping("/register")
    public Result register(@Validated @RequestBody User user) {
        String enablePassword = user.getPassword();     // 用户输入的明文密码
        String salt = Utils.salt();     // 盐
        String encryptedPassword = SecureUtil.md5(enablePassword + salt);   // 明文密码加盐再进行hash加密
        user.setPassword(encryptedPassword);    // 数据库中存储的是密文密码
        user.setSalt(salt);
        user.setRoleType(RoleType.ROLE_USER.getCode());
        userService.saveOrUpdate(user);
        return Result.success();
    }
}
