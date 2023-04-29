package com.example.xblog.service.impl;

import com.example.xblog.entity.Tag;
import com.example.xblog.mapper.TagMapper;
import com.example.xblog.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author x
 * @since 2023-04-27
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
