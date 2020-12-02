package com.example.demo.component;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

//定义动态数据源
public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return RoutingDataSourceContext.getDataSourceRoutingKey();
    }

}