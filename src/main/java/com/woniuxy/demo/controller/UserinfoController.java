package com.woniuxy.demo.controller;

import com.woniuxy.demo.model.Userinfo;
import com.woniuxy.demo.service.IRoleService;
import com.woniuxy.demo.service.IUserinfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cjw
 * @create 2020/05/27 14:03:36
 */
@RestController
@RequestMapping("/admin/userinfo")
public class UserinfoController {
    @Resource
    private IUserinfoService userinfoServiceImpl;
    @Resource
    private IRoleService roleServiceImpl;
    @GetMapping("findAll")
    public List findAll(){
        return userinfoServiceImpl.findAll();
    }
    @GetMapping("findRoles")
    public List findRoles(){
        return roleServiceImpl.findAll();
    }
    @PostMapping("save")
    public void save(Userinfo info, String rids){

        userinfoServiceImpl.save(info, rids);
    }
    @GetMapping("findById")
    public Userinfo findAll(int uid){
        return userinfoServiceImpl.findById(uid);
    }
    @PostMapping("update")
    public void update(Userinfo info, String rids){
        userinfoServiceImpl.update(info, rids);
    }
}
