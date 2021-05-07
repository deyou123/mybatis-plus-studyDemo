package com.zdy.mybatisplus.controller;


import com.zdy.mybatisplus.entity.User;
import com.zdy.mybatisplus.service.UserService;
import com.zdy.mybatisplus.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.awt.desktop.UserSessionListener;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 翟德有
 * @since 2021-02-23
 */
@RestController
@RequestMapping("/user")
public class UserController {





    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/list")
    public List<User> list(){
        return this.userService.list();
    }



}

