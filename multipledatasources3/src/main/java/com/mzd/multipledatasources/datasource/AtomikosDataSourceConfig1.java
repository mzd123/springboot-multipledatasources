package com.mzd.multipledatasources.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.mysql.cj.jdbc.MysqlXADataSource;

@Configuration
@MapperScan(basePackages = "com.mzd.multipledatasources.mapper.test01", sqlSessionTemplateRef = "test1SqlSessionTemplate")
public class AtomikosDataSourceConfig1 {

	@Primary
	@Bean(name = "test1DataSource")
	public DataSource test1DataSource(Datasource1 ds) throws SQLException {
		MysqlXADataSource mysqlxadatasource = new MysqlXADataSource();
		mysqlxadatasource.setUrl(ds.getUrl());
		mysqlxadatasource.setPassword(ds.getPassword());
		mysqlxadatasource.setUser(ds.getUsername());
		mysqlxadatasource.setPinGlobalTxToPhysicalConnection(true);
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlxadatasource);
		xaDataSource.setUniqueResourceName("test1DataSource");
		return xaDataSource;
	}

	@Bean(name = "test1SqlSessionFactory")
	@Primary
	public SqlSessionFactory test1SqlSessionFactory(@Qualifier("test1DataSource") DataSource datasource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(datasource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/test01/*.xml"));
		return bean.getObject();
	}

	@Bean("test1SqlSessionTemplate")
	@Primary
	public SqlSessionTemplate test1sqlsessiontemplate(
			@Qualifier("test1SqlSessionFactory") SqlSessionFactory sessionfactory) {
		return new SqlSessionTemplate(sessionfactory);
	}

}
