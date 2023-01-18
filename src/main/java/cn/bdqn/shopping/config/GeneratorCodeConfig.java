package cn.bdqn.shopping.config;

/**
 * 自动生成mybatisplus的相关代码
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneratorCodeConfig {
    public static void main(String[] args) {
        List<String> tablelist = new ArrayList<>();
        //生成的表名
        tablelist.add("balance");
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/or?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false", "root", "root")
                .globalConfig(builder -> {
                    builder.author("PAG") // 设置作者
                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java")// 指定输出目录
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride()// 覆盖已生成文件
                            .commentDate("yyyy-MM-dd")//日期格式
                            .fileOverride();//覆盖之前文件
                })
                .packageConfig(builder -> {
                    builder.parent("cn.bdqn.shopping") // 设置父包名
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
                                    System.getProperty("user.dir") +
                                            "\\src\\main\\java\\cn\\bdqn\\shopping\\mapper\\xml"
                            )); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tablelist) // 设置需要生成的表名
                            .addTablePrefix("") // 设置过滤表前缀
                            .serviceBuilder()// service策略配置
                            .formatServiceFileName("%sService")// 格式化 service 接口文件名称
                            .formatServiceImplFileName("%sServiceImpl")// 格式化 serviceImpl 接口文件名称
                            .entityBuilder()// 实体策略配置
                            .enableLombok()// 开启 lombok 模型
//                            .logicDeleteColumnName("deleted")// 逻辑删除字段名(数据库)
                            .enableTableFieldAnnotation()// 开启生成实体时生成字段注解
                            .controllerBuilder()// controller 策略配置
                            .formatFileName("%sController")// 格式化文件名称
                            .enableHyphenStyle()// 开启驼峰命名转字符
                            .enableRestStyle()// 开启生成@RestController 控制器
                            .mapperBuilder()// mapper 策略配置
                            .superClass(BaseMapper.class)// 设置父类
                            .formatMapperFileName("%sMapper")// 格式化 mapper 文件名称
                            .formatXmlFileName("%sMapper");// 格式化 xml 实现类文件名称
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
