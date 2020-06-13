package com.woniuxy.demo.service.impl;

import com.woniuxy.demo.mapper.TreeMapper;
import com.woniuxy.demo.model.Tree;
import com.woniuxy.demo.service.ITreeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cjw
 * @create 2020/05/27 01:35:07
 */
@Service
public class TreeServiceImpl implements ITreeService {
    @Resource
    private TreeMapper treeMapper;
    @Override
    public List<Tree> findAll() {
        return treeMapper.selectByExample(null);
    }
}
