package cn.bdqn.shopping.config;


import cn.bdqn.shopping.interceptor.PermisssionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PermissionWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PermisssionInterceptor())
                .addPathPatterns("/**")    // 拦截哪些页面
                .excludePathPatterns("/user/login", "/user/register")   // 放行哪些页面
                .excludePathPatterns("/**/swagger-ui/**")
                .excludePathPatterns("/**/v3/**")
                .excludePathPatterns("/doc.html");
    }
}

