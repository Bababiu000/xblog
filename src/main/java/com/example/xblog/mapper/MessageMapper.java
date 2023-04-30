package com.example.xblog.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.xblog.dto.MessageDTO;
import com.example.xblog.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author x
 * @since 2023-04-30
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    Page<MessageDTO> list(Page<Message> page);
}
