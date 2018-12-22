package com.gl.weave.dataSource;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
 
/**
 * 多数据源配置类
 * Created by pure on 2018-05-06.
 */
@Configuration
public class DataSourceConfig {
	@Bean
	@Primary
	@ConfigurationProperties("app.datasource.first")
	public DataSourceProperties firstDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	@ConfigurationProperties("app.datasource.first.configuration")
	public HikariDataSource firstDataSource() {
		return firstDataSourceProperties().initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();
	}

	@Bean
	@ConfigurationProperties("app.datasource.second")
	public DataSourceProperties secondDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties("app.datasource.second.configuration")
	public HikariDataSource secondDataSource() {
		return secondDataSourceProperties().initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();
	}
 
    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    
    @Bean
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(firstDataSource());
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<Object, Object>();
        dsMap.put("DB_MASTER", firstDataSource());
        dsMap.put("DB_SLAVE1", secondDataSource());
        dsMap.put("DB_SLAVE2", secondDataSource());
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        return sqlSessionFactoryBean.getObject();
    }

    
    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    /**
     * 配置@Transactional注解事物
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
