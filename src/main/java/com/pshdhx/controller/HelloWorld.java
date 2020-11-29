package com.pshdhx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Authtor pshdhx
 * @Date 2020/11/220:57
 * @Version 1.0
 */
@Controller
public class HelloWorld {
    @RequestMapping("/hello")
    @ResponseBody //内容写回给浏览器
    public String hello(){
        return "返回个浏览器的内容-helloworld";
    }


    /**
     * //如果说静态资源文件和template中都有一个index页面，那么就不知道访问那个了，需要在controller中配置一个，很麻烦，需也可以配置一个视图映射
     * @return
     */
//    @RequestMapping({"/","/index.html"})
//    public String index(){
//        return "index";
//    }
}
