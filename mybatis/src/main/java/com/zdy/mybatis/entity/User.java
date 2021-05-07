package com.zdy.mybatis.entity;

import lombok.Data;

/**
 * @Author: Deyou
 * @Date: 2021/4/6
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
