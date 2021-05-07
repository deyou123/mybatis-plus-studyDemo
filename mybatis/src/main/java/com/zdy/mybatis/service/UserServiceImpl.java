package com.zdy.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zdy.mybatis.entity.User;
import com.zdy.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Deyou
 * @Date: 2021/4/6
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public IPage<User> selectUserPage(Page<User> page, QueryWrapper wrapper) {
        return userMapper.selectPage(page,wrapper);
    }
}
