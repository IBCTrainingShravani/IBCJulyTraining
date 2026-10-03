package com.quickbite.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.hibernate.HibernateTransactionManager;
import org.springframework.orm.jpa.hibernate.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.quickbite")
@EnableTransactionManagement
public class HibernateConfig {

	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource ds = new DriverManagerDataSource();

		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");

		ds.setUrl("jdbc:mysql://localhost:3306/Quickbite");

		ds.setUsername("root");
		ds.setPassword("Seetharam@379");

		return ds;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {

		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();

		factory.setDataSource(dataSource());

		factory.setPackagesToScan("com.quickbite.model");

		Properties props = new Properties();

		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "create");

		factory.setHibernateProperties(props);

		return factory;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sf) {

		return new HibernateTransactionManager(sf);
	}
}
