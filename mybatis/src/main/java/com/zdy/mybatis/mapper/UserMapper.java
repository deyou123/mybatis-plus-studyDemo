package com.zdy.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zdy.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import static com.baomidou.mybatisplus.core.toolkit.Constants.WHERE;

/**
 * @Author: Deyou
 * @Date: 2021/4/6
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /*分页接口*/

    /***
     *
     * @param page  分页对象
     * @param name 状态
     * @return 返回分页对象
     */
    @Select( "SELECT * FROM user WHERE name=#{name}")
    IPage<User> selectPage(Page<?> page, String name);
}
