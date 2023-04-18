package com.example.xblog.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.example.xblog.entity.BaseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 代码生成器
 */
public class CodeGenerator {

    private static final String URL = "jdbc:mysql://localhost:3306/xblog?useSSL=false&useUnicode=true" +
            "&characterEncoding=utf-8&serverTimezone=GMT%2B8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "colt1911";
    private static final String author = "x"; // 作者
    private static final String parentPackage = "com.example.xblog";  // 父包名

    public static void main(String[] args) {
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.author(author)               //作者
                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java")    //输出路径(写到java目录)
                            .enableSwagger()           //开启swagger
                            .commentDate("yyyy-MM-dd")
                            .fileOverride();            //开启覆盖之前生成的文件
                })
                .packageConfig(builder -> {
                    builder.parent(parentPackage)
                            //留空请求路径中就会少一个层级 原层级/blog//user 现层级/user
                            .moduleName("")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") +
                                    "\\src\\main\\resources\\mapper"));
                })
                .strategyConfig((scanner, builder) -> {
                    builder.addInclude(getTables(scanner.apply("请输入表名，多个表名用,隔开"))) // 设置需要生成的表名
                            .addTablePrefix("t_", "c_", "sys_"); // 设置过滤表前缀
                    builder.entityBuilder()
                            .superClass(BaseEntity.class)
                            //开启Lombok，默认生成@Get，@Set，可以手动换成@Data
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Column("update_time", FieldFill.UPDATE))
                            .enableLombok()
                            .logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation();
                    builder.serviceBuilder()
                            .formatServiceFileName("%sService")
                            .build();
                    builder.controllerBuilder()
                            .enableHyphenStyle()  // 开启驼峰转连字符
                            .enableRestStyle();   // 开启生成@RestController 控制器
                    builder.mapperBuilder()
                            .enableMapperAnnotation()
                            .enableBaseResultMap()  //生成通用的resultMap
                            .enableBaseColumnList();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
