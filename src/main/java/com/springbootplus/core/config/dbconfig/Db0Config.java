package com.springbootplus.core.config.dbconfig;

import java.util.Optional;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.springbootplus.core.util.MapperUtils;
@EnableConfigurationProperties(value = Db0ConfigProperties.class)
@Configuration
@MapperScan(basePackages = {"com.springbootplus.mappers.mappers0"}, sqlSessionFactoryRef = Db0Config.SQL_SESSION_FACTORY_REF)
public class Db0Config {
	public static final String SQL_SESSION_FACTORY_REF = "sqlSessionFactory0";
	 
    public static final String DATASOUCE = "dataSource0";
 
    public static final String TX_MANAGER = "txManager0";
 
    @Autowired
    private Db0ConfigProperties db0ConfigProperties;
    
    /**
     * 配置数据源
     * @return
     */
    @Bean(DATASOUCE)
    public DataSource createDb0() {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        return builder
                .driverClassName(db0ConfigProperties.getDriver())
                .url(db0ConfigProperties.getUrl())
                .username(db0ConfigProperties.getUsername())
                .password(db0ConfigProperties.getPassword())
                .build();
    }
 
    /**
     * 配置SqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(SQL_SESSION_FACTORY_REF)
    public SqlSessionFactory createSqlSessionFactory0(@Qualifier(DATASOUCE) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(
                Stream.of(Optional.ofNullable(db0ConfigProperties.getMapperLocations()).orElse(new String[0])).flatMap(s -> {
                    try {
                        return Stream.of(MapperUtils.getResources(s));
//                        return Stream.of(applicationContext.getResources(s));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }).toArray(Resource[]::new)
        );
        return sqlSessionFactoryBean.getObject();
    }
 
    /**
     * 配置事务管理器
     * @param dataSource
     * @return
     */
    @Bean(TX_MANAGER)
    public PlatformTransactionManager createDb0TransactionManager(@Qualifier(DATASOUCE) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
