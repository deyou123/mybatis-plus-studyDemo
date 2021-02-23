package com.zdy.mybatisplus.controller;


import com.zdy.mybatisplus.entity.User;
import com.zdy.mybatisplus.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 翟德有
 * @since 2021-02-23
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/selectAll")
    //http://localhost:8080/user/selectAll
    public ModelAndView selectAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> list = userService.list();
        list.forEach(System.out::println);
        modelAndView.setViewName("index");
        modelAndView.addObject("list",list);
        return modelAndView;
    }

}

