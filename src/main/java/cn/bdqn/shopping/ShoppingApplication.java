package cn.bdqn.shopping;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import springfox.documentation.oas.annotations.EnableOpenApi;

import java.io.IOException;

@SpringBootApplication(scanBasePackages="cn.bdqn.shopping")
@Slf4j
@EnableWebMvc
@MapperScan("cn.bdqn.shopping.mapper")
public class ShoppingApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingApplication.class, args);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
