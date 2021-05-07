package com.zdy.mybatisplus;

import com.zdy.mybatisplus.entity.User;
import com.zdy.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {
    @Autowired
    private DataSource dataSource;
    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        if(connection != null){
            System.out.println("数据库配置文件成功！");
        }
        connection.close();
    }
    @Autowired
    private UserMapper userMapper;
    @Test
    void insert(){
        User user = new User();
        user.setName("lisi");
        userMapper.insert(user);
    }

    @Test
    void selectAll(){

        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }




}
