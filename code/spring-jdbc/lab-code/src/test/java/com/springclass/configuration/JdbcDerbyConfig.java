package com.springclass.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@Profile("derby")
@EnableTransactionManagement
public class JdbcDerbyConfig {


//    @Bean
//    public DataSource dataSource() {
//        BasicDataSource ds = new BasicDataSource();
//        ds.setDriverClassName(org.h2.Driver.class.getCanonicalName());
//        ds.setUrl("jdbc:derby://localhost/springdemos");
//        ds.setUsername("sa");
//        ds.setPassword("password");
//        ds.setInitialSize(2);
//        ds.setMaxTotal(10);
//        ds.setMaxIdle(5);
//
//        return ds;
//    }
//    @PreDestroy()
//    public void dataSourceDestroy() throws SQLException{
//        dataSource().getConnection().close();
//
//    }

//    @Bean
//    public DataSource dataSource2() {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName(org.h2.Driver.class.getCanonicalName());
//        ds.setUrl("jdbc:derby://localhost/springdemos");
//        ds.setUsername("sa");
//        ds.setPassword("password");
//        return ds;
//    }

} // The End...
