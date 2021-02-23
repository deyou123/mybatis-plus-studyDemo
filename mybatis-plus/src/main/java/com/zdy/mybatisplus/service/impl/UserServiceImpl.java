package com.zdy.mybatisplus.service.impl;

import com.zdy.mybatisplus.entity.User;
import com.zdy.mybatisplus.mapper.UserMapper;
import com.zdy.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 翟德有
 * @since 2021-02-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
