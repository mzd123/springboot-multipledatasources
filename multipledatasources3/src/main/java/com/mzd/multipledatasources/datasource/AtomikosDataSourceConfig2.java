package com.mzd.multipledatasources.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.mysql.cj.jdbc.MysqlXADataSource;

@Configuration
@MapperScan(basePackages = "com.mzd.multipledatasources.mapper.test02", sqlSessionTemplateRef = "test2SqlSessionTemplate")
public class AtomikosDataSourceConfig2 {


	@Bean(name = "test2DataSource")
	public DataSource test1DataSource(Datasource2 ds) throws SQLException {
		MysqlXADataSource mysqlxadatasource = new MysqlXADataSource();
		mysqlxadatasource.setUrl(ds.getUrl());
		mysqlxadatasource.setPassword(ds.getPassword());
		mysqlxadatasource.setUser(ds.getUsername());
		mysqlxadatasource.setPinGlobalTxToPhysicalConnection(true);
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlxadatasource);
		xaDataSource.setUniqueResourceName("test2DataSource");
		return xaDataSource;
	}

	@Bean(name = "test2SqlSessionFactory")
	public SqlSessionFactory test2SqlSessionFactory(@Qualifier("test2DataSource") DataSource datasource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(datasource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/test02/*.xml"));
		return bean.getObject();
	}

	@Bean("test2SqlSessionTemplate")
	public SqlSessionTemplate test2sqlsessiontemplate(
			@Qualifier("test2SqlSessionFactory") SqlSessionFactory sessionfactory) {
		return new SqlSessionTemplate(sessionfactory);
	}

}
