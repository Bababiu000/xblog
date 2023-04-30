package com.example.xblog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.xblog.dto.MessageDTO;
import com.example.xblog.dto.PagedQuery;
import com.example.xblog.entity.Message;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author x
 * @since 2023-04-30
 */
public interface MessageService extends IService<Message> {
    Page<MessageDTO> list (PagedQuery pageQuery);
}
