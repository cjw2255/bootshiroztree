package com.woniuxy.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cjw
 * @create 2020/05/26 20:56:42
 */
@Controller
public class LoginController {
    @PostMapping("/login")
    public String login(String uname, String upwd, ModelMap map){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(uname,upwd);
        try{
            subject.login(token);
            return "redirect:/admin/index.jsp";
        }catch (UnknownAccountException ex){
            map.put("error","该账户不存在");
            return "/login";
        } catch (IncorrectCredentialsException ex){
            map.put("error","密码错误");
            return "/login";
        }catch (Exception ex){
            map.put("error","登录失败");
            return "/login";
        }
    }
    @RequestMapping("/admin/loginout")
    public String loginout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/tologin";
    }
}
