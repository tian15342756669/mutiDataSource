package com.jiexun.config;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dengtianguang on 2018/4/4.
 * 重写sqlSessionFactory
 */
@Configuration
@AutoConfigureAfter({ DataSourceConfiguration.class })
public class MybatisConfiguration extends MybatisAutoConfiguration {
    @Value("${datasource.readSize}")
    private String dataSourceSize;

    public MybatisConfiguration(MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider, ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider, ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
        super(properties, interceptorsProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactorys() throws Exception {
        System.out.println("-------------------- 重载父类 sqlSessionFactory init ---------------------");
        return super.sqlSessionFactory(roundRobinDataSourceProxy());
    }

    /**
     * 有多少个数据源就要配置多少个bean
     * @return
     */
    @Bean
    public AbstractRoutingDataSource roundRobinDataSourceProxy() {
        int size = Integer.parseInt(dataSourceSize);

        System.out.println( "+++++++ "+size+" ++++++++++");
        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource(size);

        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        System.out.println("SpringContextHolder:"  +  SpringContextHolder.class +
        "#################" + SpringContextHolder.getBean("writeDataSource").toString());

        DataSource writeDataSource = SpringContextHolder.getBean("writeDataSource");
        // 写
        targetDataSources.put(DataSourceType.write.getType(), SpringContextHolder.getBean("writeDataSource"));

        for (int i = 0; i < size; i++) {
            targetDataSources.put(i, SpringContextHolder.getBean("readDataSource" + (i + 1)));
        }
        proxy.setDefaultTargetDataSource(writeDataSource);
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }

}