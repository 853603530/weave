package com.gl.weave.dataSource;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
 
/**
 * Created by pure on 2018-05-06.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
    	System.out.println("数据源为"+DataSourceContextHolder.get());
        return DataSourceContextHolder.get();
    }

}
