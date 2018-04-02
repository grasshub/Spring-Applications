package com.springclass.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// @Profile("embedded")
@Configuration
@EnableTransactionManagement
public class JdbcConfig {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase database = builder.setType(EmbeddedDatabaseType.H2)
                .ignoreFailedDrops(true)
                .continueOnError(false)
                .addScript("classpath:schema.sql")
                .addScript("classpath:seed-data.sql")
                .build();
        return database;
    }


    //*** MUST ADD THIS ONE BEAN *****************************************************/
    @Bean @Autowired
    public PlatformTransactionManager transactionManager(final DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    // TODO: Lab: Add LocalValidatorFactoryBean here:

}
