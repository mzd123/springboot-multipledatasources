package com.mzd.multipledatasources.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = "com.mzd.multipledatasources.mapper", sqlSessionFactoryRef = "SqlSessionFactory")
public class DataSourceConfig {
	@Primary
	@Bean(name = "test1DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.test1")
	public DataSource getDateSource1() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "test2DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.test2")
	public DataSource getDateSource2() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "dynamicDataSource")
	public DynamicDataSource DataSource(@Qualifier("test1DataSource") DataSource test1DataSource,
			@Qualifier("test2DataSource") DataSource test2DataSource) {
		Map<Object, Object> targetDataSource = new HashMap<>();
		targetDataSource.put(DataSourceType.DataBaseType.TEST01, test1DataSource);
		targetDataSource.put(DataSourceType.DataBaseType.TEST02, test2DataSource);
		DynamicDataSource dataSource = new DynamicDataSource();
		dataSource.setTargetDataSources(targetDataSource);
		dataSource.setDefaultTargetDataSource(test1DataSource);
		return dataSource;
	}

	@Bean(name = "SqlSessionFactory")
	public SqlSessionFactory test1SqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dynamicDataSource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/*.xml"));
		return bean.getObject();
	}

}
