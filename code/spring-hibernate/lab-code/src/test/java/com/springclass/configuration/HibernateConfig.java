package com.springclass.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.springclass.model.Member;

@Configuration
@EnableTransactionManagement
public class HibernateConfig implements InitializingBean, DisposableBean {

	@Autowired
	private Environment environment;

	EmbeddedDatabase database = null;

	/*
	 * Add a SessionFactory
	 *
	 * Include the dataSource property and add Member.class as an annotated
	 * Hibernate class
	 */
	@Autowired 
	@Bean
	public SessionFactory sessionFactory(DataSource dataSource) {
		return new LocalSessionFactoryBuilder(dataSource)
				.addAnnotatedClass(Member.class)
				.addProperties(hibernateProperties())
				.buildSessionFactory();
	}

	/*
	 * Add a HibernateTransactionManager
	 *
	 *
	 * Include the sessionFactory property
	 */
	@Autowired 
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		return new HibernateTransactionManager(s);
	}

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		database = builder.setType(EmbeddedDatabaseType.H2).ignoreFailedDrops(true).continueOnError(false).build();
		return database;
	}

	@Bean
	@Profile("jndi")
	public DataSource jndiDataSource() {
		JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
		return jndiDataSourceLookup.getDataSource("java:comp/env/SpringClassDataSource");
	}

	@Bean
	@Profile("jta")
	public TransactionManager jtaTransactionManager() {
		JtaTransactionManager transactionManager = new JtaTransactionManager();
		transactionManager.setTransactionManagerName("java:comp/env/SpringClassTransactionManager");
		return transactionManager.getTransactionManager();
	}

	@Autowired // autowired:: constructor
	@Bean
	public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
		return new HibernateTemplate(sessionFactory);
	}

	@Value("${spring.hibernate.dialect}")
	private String dialect;

	@Value("${spring.hibernate.show_sql}")
	private String show_sql;

	@Value("${spring.hibernate.format_sql}")
	private String format_sql;

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("javax.persistence.schema-generation.database.action", "update");
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.show_sql", show_sql);
		properties.put("hibernate.format_sql", format_sql);
		return properties;
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

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(Boolean.TRUE);
		propertySourcesPlaceholderConfigurer.setProperties(yamlPropertiesFactoryBean().getObject());
		return propertySourcesPlaceholderConfigurer;
	}

	@Bean
	public static YamlPropertiesFactoryBean yamlPropertiesFactoryBean() {
		YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
		yaml.setResources(new ClassPathResource("application.yml"));
		return yaml;
	}

} // the end...
