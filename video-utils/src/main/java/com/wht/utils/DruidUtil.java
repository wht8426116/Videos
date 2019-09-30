package com.wht.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;

public class DruidUtil extends PooledDataSourceFactory {
    public DruidUtil() {
        this.dataSource = new DruidDataSource();
    }
}
