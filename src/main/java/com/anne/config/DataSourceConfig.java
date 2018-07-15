package com.anne.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.anne.config.properties.DataSourceProperties;

/**
 * Database 정보 config
 */

public abstract class DataSourceConfig {

	@Bean
	public abstract BasicDataSource dataSource();

	protected void configureDataSource(BasicDataSource ds, DataSourceProperties dsConfig) {
		ds.setDriverClassName("org.mariadb.jdbc.Driver");
		ds.setUrl("jdbc:mariadb://127.0.0.1:3306/book");
		ds.setUsername("root");
		ds.setPassword("anne1234");
		ds.setInitialSize(10);
		ds.setMaxTotal(10);
		ds.setMaxIdle(10);
		ds.setMinIdle(10);
		ds.setMaxWaitMillis(3000);
		ds.setTestOnBorrow(true);
		ds.setTestWhileIdle(false);
		ds.setValidationQuery("SELECT 1");
	}
}

/**
 * book db 정보 setting
 */
@Configuration
@EnableTransactionManagement
class BookDataSourceConfig extends DataSourceConfig {

	@Autowired
	private AppConfig appConfig;
	private final String DATABASE_BOOK = "book";

	@Bean(name = "bookDataSource", destroyMethod = "close")
	@Primary
	public BasicDataSource dataSource() {
		
		BasicDataSource ds = new BasicDataSource();
		DataSourceProperties dsConfig = appConfig.getDatasource().get(DATABASE_BOOK);
		configureDataSource(ds, dsConfig);
		
		return ds;
	}

	@Bean(name = "bookTransactionManager")
	public PlatformTransactionManager pubTransactionManager(@Qualifier("bookDataSource") DataSource dataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		transactionManager.setGlobalRollbackOnParticipationFailure(false);
		return transactionManager;
	}
}