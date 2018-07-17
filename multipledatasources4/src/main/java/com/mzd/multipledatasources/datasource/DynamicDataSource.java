package com.mzd.multipledatasources.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		DataSourceType.DataBaseType dataBaseType = DataSourceType.getDataBaseType();
		return dataBaseType;
	}

}
