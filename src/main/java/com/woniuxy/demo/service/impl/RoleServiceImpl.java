package com.woniuxy.demo.service.impl;

import com.woniuxy.demo.mapper.RoleMapper;
import com.woniuxy.demo.model.Role;
import com.woniuxy.demo.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cjw
 * @create 2020/05/27 14:33:17
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Resource
    private RoleMapper roleMapper;
    @Override
    public List<Role> findAll() {
        return roleMapper.selectByExample(null);
    }
}
