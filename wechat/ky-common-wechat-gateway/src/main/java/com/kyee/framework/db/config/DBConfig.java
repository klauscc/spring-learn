package com.kyee.framework.db.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author 梁志艳
 * 创建时间：2015-07-20 下午1:32 
 * 任务号：MOBILEDEVELOP-9620
 * 创建说明：创建数据源配置类
 */
@Configuration
@MapperScan(basePackages = "com.kyee.framework.security.repository.mapper")
public class DBConfig {
    /**
     * 配置数据源
     * @return 数据源的bean
     */
    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://202.120.40.105:9003/ky_common_wechat");
        dataSource.setUsername("kyee-run");
        dataSource.setPassword("kyeepass");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;
    }

    /**
     * 事务管理bean
     * @return 事务管理的bean
     */
    /*
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }*/

    /**
     * MyBatis SqlSession 工厂
     * @return MyBatis SqlSession 工厂
     * @throws Exception MyBatis初始化的时候有可能报错
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean.getObject();
    }
}
