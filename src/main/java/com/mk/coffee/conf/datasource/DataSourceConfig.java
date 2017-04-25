package com.mk.coffee.conf.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/3/17 0017.
 */
@Configuration
public class DataSourceConfig {
    @Autowired
    private DataSourceProperties properties;

    @Bean
    public HikariDataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getUrl());
        config.setUsername(properties.getUsername());
        config.setPassword(properties.getPassword());
        config.setDriverClassName("com.mysql.jdbc.Driver");
        //开启缓存
        config.addDataSourceProperty("cachePrepStmts", "true");
        //设置预处理语句的数量默认为25，建议设置在250 - 500之间
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        //SQL语句的最大长度，MySQL的默认是256，建议设置为2048
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }
}
