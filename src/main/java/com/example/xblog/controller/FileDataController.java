package com.example.xblog.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.xblog.common.Result;
import com.example.xblog.entity.FileData;
import com.example.xblog.service.FileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author x
 * @since 2023-04-18
 */
@RestController
@RequestMapping("/file")
public class FileDataController {

    @Value("${file.save.location}")
    private String fileSaveLocation;

    @Value("${file.request.url}")
    private String fileRequestUrl;

    @Autowired
    private FileDataService fileDataService;

    /**
     * 文件上传接口
     * @param file 前端传递过来的文件
     */
    @PostMapping("/upload")
    public Result upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = (file.getSize() / 1024);    // 单位KB

        // 定义文件唯一标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUuid = uuid + StrUtil.DOT + type;

        File uploadFile = new File(fileSaveLocation + fileUuid);
        // 判断配置的文件路径是否存在，若不存在就创建一个新目录
        if(!uploadFile.getParentFile().exists()) {
            uploadFile.getParentFile().mkdirs();
        }

        String url;
        // 获取文件的md5
        String md5 = SecureUtil.md5(file.getInputStream());
        // 从数据库查询是否存在相同的记录
        FileData dbFile = getFileByMd5(md5);
        if (dbFile != null) { // 文件已存在
            url = dbFile.getUrl();
        } else {
            // 上传文件到磁盘
            file.transferTo(uploadFile);
            url = fileRequestUrl + fileUuid;
            // 存储到数据库
            FileData saveFile = new FileData();
            saveFile.setName(originalFilename);
            saveFile.setType(type);
            saveFile.setSize(size);
            saveFile.setUrl(url);
            saveFile.setMd5(md5);
            fileDataService.save(saveFile);
        }
        return Result.success("上传成功！", url);
    }

    private FileData getFileByMd5(String md5) {
        // 查询文件的md5是否存在
        LambdaQueryWrapper<FileData> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(FileData::getMd5, md5);
        FileData fileData = fileDataService.getOne(lambdaQueryWrapper);
        return fileData;
    }
}
