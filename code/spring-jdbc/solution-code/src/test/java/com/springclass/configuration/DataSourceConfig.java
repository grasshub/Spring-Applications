package com.springclass.configuration;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    @Value("${spring.name}")
    private String currentProfile;

    @Value("${spring.datasource.driverClassName}")
    private String driverClass;
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    // In case we use an embedded Database:
    EmbeddedDatabase database = null;

    @Autowired
    private ApplicationContext applicationContext;

    //-----------------------------------------------------------------------//

    @Bean(name = "dataSource")
    public DataSource embeddedDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        database = builder.setType(EmbeddedDatabaseType.H2)
                .ignoreFailedDrops(true)
                .continueOnError(false)
                .addScript("classpath:schema.sql")
                .addScript("classpath:seed-data.sql")
//                .addScript("classpath:seed-data-dvd.sql")
//                .addScript("classpath:seed-data-dvdtitle.sql")
                .build();
        return database;
    }


//    @Bean(name = "dataSource")
//    @Profile("derby")
//    @Primary
    public DataSource derbyDataSource() {
        BoneCPDataSource ds = new BoneCPDataSource();
        ds.setDriverClass(driverClass);
        ds.setJdbcUrl(jdbcUrl);
        ds.setUsername(username);
        ds.setPassword(password);

        ds.setIdleConnectionTestPeriodInSeconds(60);
        ds.setIdleMaxAge(10, TimeUnit.SECONDS);
        ds.setMaxConnectionsPerPartition(5);
        ds.setMinConnectionsPerPartition(2);
        ds.setPartitionCount(3);
        ds.setAcquireIncrement(2);
        ds.setStatementsCacheSize(10);

        return ds;
    }



    /**
     * DataSource PostConstruct call-back
     * @throws SQLException
     */
    @PostConstruct
    public void dataSourceInitialization() throws SQLException {
        // custom init logic
    }

    /**
     * DataSource PreDestroy call-back
     * @throws SQLException
     */
    @PreDestroy()
    public void dataSourceDestroy() throws SQLException {

        SQLException sqlException = null;

        try {
            applicationContext.getBean(DataSource.class)
                    .getConnection()
                    .close();
        } catch (SQLException e){
            sqlException = e;
            e.printStackTrace();
        }

        if (database != null) {
            database.shutdown();
        }

        if(sqlException != null){
            throw sqlException;
        }
    }

    /**
     * This datasource uses the standard DBCP2 BasicDataSource
     */
//    @Bean(name = "dataSource")
// @Profile("dbcp2")
//    public DataSource dataSource_BasicDataSource() {
//        BasicDataSource ds = new BasicDataSource();
//        ds.setDriverClass(driverClass);
//        ds.setJdbcUrl(jdbcUrl);
//        ds.setUsername(username);
//        ds.setPassword(password);
//        ds.setInitialSize(2);
//        ds.setMaxTotal(10);
//        ds.setMaxIdle(5);
//        return ds;
//    }

    /**
     * This datasource uses the Spring DriverManagerDataSource
     */
//    @Bean(name = "dataSource")
// @Profile("driverManager")
//    public DataSource dataSource_DriverManagerDataSource() {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClass(driverClass);
//        ds.setJdbcUrl(jdbcUrl);
//        ds.setUsername(username);
//        ds.setPassword(password);
//        return ds;
//    }

//    @Bean(name = "dataSource")
// @Profile("jndiLookup")
//    public DataSource jndiDataSource() {
//        JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
//        return jndiDataSourceLookup.getDataSource("java:jdbc/SpringClassDataSource");
//    }

//    @Bean(name = "dataSource")
// @Profile("jta")
//    public TransactionManager jtaTransactionManager() {
//        JtaTransactionManager transactionManager = new JtaTransactionManager();
//        transactionManager.setTransactionManagerName("java:env/SpringClassTransactionManager");
//        return transactionManager.getTransactionManager();
//    }

//    @Bean(name = "dataSource")
// @Profile("jndiFactory")
//    public DataSource dataSource() {
//        JndiObjectFactoryBean jndiFactory = new JndiObjectFactoryBean();
//        jndiFactory.setJndiName("jdbc/SpringClassDataSource");
//        jndiFactory.setResourceRef(true);
//        jndiFactory.setProxyInterface(javax.sql.DataSource.class);
//        return (DataSource) jndiFactory.getObject();
//    }


} // The End...
