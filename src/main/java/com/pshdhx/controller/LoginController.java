package com.pshdhx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Authtor pshdhx
 * @Date 2020/11/2913:14
 * @Version 1.0
 */
@Controller
public class LoginController {
    @PostMapping(value="/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map,
                        HttpSession session){
        if(!StringUtils.isEmpty(username)&&"123456".equals(password)){
            //在登录之后，一点刷新，会提示用户是否离开页面，这是因为url还是index，为了防止表单重复提交，我们需要重定向；=>利用视图解析器
            //不仅如此，页面还很简陋，一些前端页面配置不生效，需要重定向。但是登陆之后的这个url别人可以随意访问，我们需要对其拦截校验
            session.setAttribute("username",username);
            return "redirect:/main.html";
        }else{
            map.put("msg","用户名密码错误");
            return "login";
        }
    }
}
