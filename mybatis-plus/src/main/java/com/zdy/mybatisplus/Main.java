package com.zdy.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;

import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;


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
