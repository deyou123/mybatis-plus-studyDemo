package com.zdy.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zdy.mybatis.entity.User;

/**
 * @Author: Deyou
 * @Date: 2021/4/6
 */
public interface UserService {
    public IPage<User> selectUserPage(Page<User> page, QueryWrapper state);
}
