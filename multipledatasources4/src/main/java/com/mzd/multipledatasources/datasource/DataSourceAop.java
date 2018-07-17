package com.mzd.multipledatasources.datasource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.mzd.multipledatasources.datasource.DataSourceType.DataBaseType;

@Aspect
@Component
public class DataSourceAop {
	@Before("execution(* com.mzd.multipledatasources.service..*.test01*(..))")
	public void setDataSource2test01() {
		System.err.println("test01业务");
		DataSourceType.setDataBaseType(DataBaseType.TEST01);
	}
	
	@Before("execution(* com.mzd.multipledatasources.service..*.test02*(..))")
	public void setDataSource2test02() {
		System.err.println("test02业务");
		DataSourceType.setDataBaseType(DataBaseType.TEST02);
	}
}
