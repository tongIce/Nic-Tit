package com.litt.micro.datasourse;

import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * 达到动态切换数据库
 * @author Cardiac
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	 
    @Override
    protected Object determineCurrentLookupKey() {
        // 从自定义的位置获取数据源标识
        return DynamicDataSourceHolder.getDataSource();
    }

}