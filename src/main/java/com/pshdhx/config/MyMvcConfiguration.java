package com.pshdhx.config;

import com.pshdhx.component.LoginHandlerInterceptor;
import com.pshdhx.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @Authtor pshdhx
 * @Date 2020/11/2818:48
 * @Version 1.0
 */
@Configuration
//@EnableWebMvc
public class MyMvcConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送com.pshdhx请求，也来到success页面。
        registry.addViewController("/pshdhx").setViewName("success");
    }



    /**
     * 所有的webmvcadapter组件都会一起起作用，前提是springboot知道我们自定义组件的存在
     */
    @Bean//将我们的组件注册到容器中
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter =  new WebMvcConfigurerAdapter(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
            //注册拦截器的
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);"/","/login.html","/user/login","/asserts/**","/webjars/**"
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")//拦截任意多层路径下的任意请求
                        .excludePathPatterns("/","/index.html","/user/login"); //登录页面和登录请求页面不能拦截
                //如果/index.html不加‘/’,这里就产生了死循环；
                //springboot已经做好了静态资源的映射，我们不需要管，直接访问即可。
            }
        };

        return adapter;
    }
    /**
     * 添加localeResolver到容器中
     */
    @Bean
    public LocaleResolver localeResolver(){
        return  new MyLocaleResolver();
    }
}
