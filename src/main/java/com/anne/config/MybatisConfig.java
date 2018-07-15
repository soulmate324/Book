package com.anne.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.anne.annotation.Book;


/**
 * mybatis config
 */
public abstract class MybatisConfig {

	public static final String BASE_PACKAGE = "com.anne.dao";
	public static final String MAPPER_LOCATIONS_PATH = "classpath*:sql/*.xml";

	protected void configureSqlSessionFactory(SqlSessionFactoryBean sessionFactoryBean, DataSource dataSource)
			throws IOException { 
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	    
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setMapperLocations(resolver.getResources(MybatisConfig.MAPPER_LOCATIONS_PATH));
	}
}

/**
 * Book db mybatis setting
 *
 */
@Configuration
@MapperScan(basePackages = {MybatisConfig.BASE_PACKAGE }, annotationClass = Book.class, 
			sqlSessionFactoryRef = "bookSqlSessionFactory")
class MybatisBookConfig extends MybatisConfig {

	@Bean(name = "bookSqlSessionFactory")
	public SqlSessionFactory bookSqlSessionFactory(@Qualifier("bookDataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		configureSqlSessionFactory(sessionFactoryBean, dataSource);
		return sessionFactoryBean.getObject();
	}
	
	@Bean(name = "bookSqlSessionTemplate") 
	public SqlSessionTemplate bookSqlSessionTemplate(SqlSessionFactory bookSqlSessionFactory) throws Exception { 
		return new SqlSessionTemplate(bookSqlSessionFactory); 
	}
}