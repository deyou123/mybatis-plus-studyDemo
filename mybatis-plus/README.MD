# mybatis-plus 逆向生成代码

## 1、创建数库

db.sql

```java
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id BIGINT(20) NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);


INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');

```

## 2、创建一个springboot 项目

```java
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.4.2</version>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.4.1</version>
        </dependency>
        <dependency>
            <groupId>org.apache.velocity</groupId>
            <artifactId>velocity</artifactId>
            <version>1.7</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-thymeleaf -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
            <version>2.4.3</version>
        </dependency>
```

## 3、配置 yaml 文件

```yaml
spring:
  #配置数据库
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/db_mybatis?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC
    username: root
    password: 666666

  # 配置Thymeleaf缓存，开发使用。
  cache: false
  thymeleaf:
    # 默认值。可以不用配置，如需更改可以配置。
    prefix: classpath:/templates/
    suffix: .html
# 配置日志
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

## 5、 测试：数据库配置是否成功

```java
@SpringBootTest
class MybatisPlusApplicationTests {
    @Autowired
    private DataSource dataSource;
    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        //获取数据库连接对象
        Connection connection = dataSource.getConnection();
        if(connection != null){
            System.out.println("数据库配置文件成功！");
        }
        connection.close();
    }

}
```

## 6. 下面开始编写逆向工程代码：

```java
public class Main {
    public static void main(String[] args) {
        //1.
        AutoGenerator autoGenerator = new AutoGenerator();

        //1. 配置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        //配置数据库类型
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/lou_springboot?" +
                "useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("666666");

        //2. 配置全局代理
        GlobalConfig globalConfig = new GlobalConfig();
        //设置作者
        globalConfig.setAuthor("翟德有");
        //输出路径
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
        //生成文件是否打开文件目录
        globalConfig.setOpen(false);
        globalConfig.setServiceName("%sService");

        //包信息
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.zdy.mybatisplus");
        //新建一个包存放所有生成的文件。
        //packageConfig.setModuleName("test");

        //配置策略
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);

        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.execute();
    }
}
```

执行 上述文件主方法，生成代码

![image-20210223140142061](C:\Users\zhaid\AppData\Roaming\Typora\typora-user-images\image-20210223140142061.png)

# 7、 测试spring boot 项目

新建一个HelloController 类

```java
@Controller
public class HelloController {
    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello mybatis-plus!";
    }
}
```

访问 http://localhost:8080/hello

## 8、测试逆向工程生成的代码

在控制层 UserController.java 类中添加如下方法

```java
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
```

在resources/templates/下新建index.html 文件

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Thymeleaf 前端页面</title>
</head>
<body>
    <p>数据展示：</p>
    <table>
        <tr th:each="user:${list}">
            <td th:text="${user.id}"></td>
            <td th:text="${user.name}"></td>
            <td th:text="${user.email}"></td>
        </tr>
    </table>
</body>
</html>
```

访问 http://localhost:8080/user/selectAll  

页面显示结果如下

![image-20210223141038765](C:\Users\zhaid\AppData\Roaming\Typora\typora-user-images\image-20210223141038765.png)

# 常用注解



@TableName 映射表和类

@TabbleId   映射主键

```html
AUTO(0)			数据库自增
NONE(1)			默认，不设置，开发者赋值则使用开发者给的值，否则生成随机数
INPUT(2)		insert之前需要手动赋值
ASSIGN_ID(3)	使用雪花算法生成唯一值，可以是数字类型或字符串类型
ASSIGN_UUID(4)  使用雪花算法生成唯一值，只能是字符串类型
```

@TableField

```html
数据库表不一致时使用
```

# 字段自动填充功能实现

查询数据库的时候不会查询 title 字段，实体类和数据表字段不一致的时候使用

fill 自动填充

```java
@TableField(fill = FieldFill.INSERT)
private Date createTime;  
@TableField(fill = FieldFill.INSERT_UPDATE)
private Date updateTime;
```

添加自动填充处理器

```java
package com.southwind.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaDataHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(),metaObject );
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
```

> # 分页查询

功能：查询名字为Sandy 的数据，显示第二页，页面数据3

注入拦截器

```java
@Configuration
public class PageConfig {

    // 最新版
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL ));
        return interceptor;
    }
}

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /*自定义分页接口*/
	
    /***
     *
     * @param page  分页对象
     * @param name 状态
     * @return 返回分页对象
     */
    @Select( "SELECT * FROM user WHERE name=#{name}")
    IPage<User> selectPage(Page<?> page, String name);
}

```

```java
//测试分页功能
@Test
void page(){
    Page<User> page = new Page<>(2,3);
    IPage<User> userIPage = userMapper.selectPage(page, "Sandy");
    userIPage.getRecords().forEach(System.out::println);
}
```





