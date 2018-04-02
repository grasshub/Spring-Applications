package com.springclass.configuration;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.transaction.jta.JtaTransactionManager;

import javax.naming.NamingException;
import javax.sql.DataSource;
//import javax.transaction.TransactionManager;

@Configuration
@EnableTransactionManagement
public class JdbcConfig implements InitializingBean, DisposableBean {

    EmbeddedDatabase database = null;

    // Add dataSource()
    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        database = builder.setType(EmbeddedDatabaseType.H2)
                .ignoreFailedDrops(true)
                .continueOnError(false)
                .addScript("classpath:schema.sql")
                .addScript("classpath:seed-data.sql")
                .build();
        return database;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }



    @Override
    public void afterPropertiesSet() {
        // custom init logic
    }

    @Override
    public void destroy() {
        if (database != null) {
            database.shutdown();
        }
    }

} // The end...
