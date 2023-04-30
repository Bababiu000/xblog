package com.example.xblog.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xblog.dto.MessageDTO;
import com.example.xblog.dto.PagedQuery;
import com.example.xblog.entity.Message;
import com.example.xblog.mapper.MessageMapper;
import com.example.xblog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author x
 * @since 2023-04-30
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public Page<MessageDTO> list(PagedQuery pagedQuery) {
        Page<Message> page = new Page<>(pagedQuery.getPageNum(), pagedQuery.getPageSize());
        return messageMapper.list(page);
    }
}
