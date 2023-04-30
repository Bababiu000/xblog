package com.example.xblog.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.xblog.common.Result;
import com.example.xblog.dto.MessageDTO;
import com.example.xblog.dto.PagedQuery;
import com.example.xblog.entity.Message;
import com.example.xblog.entity.User;
import com.example.xblog.enums.ResultType;
import com.example.xblog.exception.ServiceException;
import com.example.xblog.service.MessageService;
import com.example.xblog.service.UserService;
import com.example.xblog.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author x
 * @since 2023-04-30
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    // 分页查询
    @PostMapping("/list")
    public Result pagedQuery (@RequestBody PagedQuery pagedQuery) {
        Page<MessageDTO> messageList = messageService.list(pagedQuery);
        return Result.success(messageList);
    }

    // 更新或添加
    @PostMapping("/save")
    public Result saveOrMod(@RequestBody Message message) {
        Integer curUserId = JwtUtils.getCurrentUserInfo();
        if(curUserId == null)
            throw new ServiceException(ResultType.UNAUTHORIZED.getCode(), ResultType.UNAUTHORIZED.getMessage());
        User curUser = userService.getById(curUserId);
        User msgUser = userService.getById(message.getUserId());
        if(message.getId() == null || curUser.getRoleType() > msgUser.getRoleType() || message.getUserId().equals(curUserId))
            messageService.saveOrUpdate(message);
        else
            throw new ServiceException(ResultType.FORBIDDEN.getCode(), ResultType.FORBIDDEN.getMessage());
        return Result.success("发送成功！");
    }

    // 批量删除
    @PostMapping("/delBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        Integer curUserId = JwtUtils.getCurrentUserInfo();
        if(curUserId == null)
            return Result.error(ResultType.UNAUTHORIZED.getCode(), ResultType.UNAUTHORIZED.getMessage());
        User curUser = userService.getById(curUserId);
        for(Integer id: ids) {
            //普通用户
            Message message = messageService.getById(id);
            User msgUser = userService.getById(message.getUserId());

            if(curUser.getRoleType() > msgUser.getRoleType() || message.getUserId().equals(curUserId))
                messageService.removeById(id);
            else
                throw new ServiceException(ResultType.FORBIDDEN.getCode(), ResultType.FORBIDDEN.getMessage());
        }
        return Result.success();
    }
}
