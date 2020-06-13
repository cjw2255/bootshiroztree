package com.woniuxy.demo.service;

import com.woniuxy.demo.model.Userinfo;

import java.util.List;

/**
 * @author cjw
 * @create 2020/05/26 22:18:42
 */
public interface IUserinfoService {
    public Userinfo findByUname(String uname);
    public void save(Userinfo info, String rids);
    public void update(Userinfo info, String rids);
    public Userinfo findById(Integer uid);
    public List<Userinfo> findAll();
}
