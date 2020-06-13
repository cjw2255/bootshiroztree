package com.woniuxy.demo.service.impl;

import com.woniuxy.demo.mapper.UserinfoMapper;
import com.woniuxy.demo.mapper.UserinforoleMapper;
import com.woniuxy.demo.model.Userinfo;
import com.woniuxy.demo.model.UserinforoleExample;
import com.woniuxy.demo.model.UserinforoleKey;
import com.woniuxy.demo.service.IUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;

/**
 * @author cjw
 * @create 2020/05/26 22:19:16
 */
@Service
public class UserinfoServiceImpl implements IUserinfoService {
    @Resource
    private UserinfoMapper userinfoMapper;
    @Resource
    private UserinforoleMapper userinforoleMapper;
    @Override
    public Userinfo findByUname(String uname) {
        Userinfo userinfo = userinfoMapper.findByUname(uname);
        return userinfo;
    }

    @Override
    public void save(Userinfo info, String rids) {
        userinfoMapper.insert(info);
        String[] ridsArr = rids.split(",");
        for (int i = 0; i < ridsArr.length; i++) {
            String rid = ridsArr[i];
            UserinforoleKey userinforoleKey = new UserinforoleKey();
            userinforoleKey.setUid(info.getUid());
            userinforoleKey.setRid(Integer.parseInt(rid));
            userinforoleMapper.insert(userinforoleKey);
        }
    }

    @Override
    public void update(Userinfo info, String rids) {
        userinfoMapper.updateByPrimaryKey(info);

        //修改时先将用户角色表中的信息删除掉，然后再进行添加数据
        UserinforoleExample userinforoleExample = new UserinforoleExample();
        userinforoleExample.createCriteria().andUidEqualTo(info.getUid());
        userinforoleMapper.deleteByExample(userinforoleExample);

        String[] ridsArr = rids.split(",");
        for (int i = 0; i < ridsArr.length; i++) {
            String rid = ridsArr[i];
            UserinforoleKey userinforoleKey = new UserinforoleKey();
            userinforoleKey.setUid(info.getUid());
            userinforoleKey.setRid(Integer.parseInt(rid));
            userinforoleMapper.insert(userinforoleKey);
        }
    }

    @Override
    public Userinfo findById(Integer uid) {
        return userinfoMapper.selectByPrimaryKey(uid);
    }

    @Override
    public List<Userinfo> findAll() {
        return userinfoMapper.selectByExample(null);
    }
}
