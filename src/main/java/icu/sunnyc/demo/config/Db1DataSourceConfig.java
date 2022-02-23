package icu.sunnyc.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author: houcheng
 * @date: 2022/2/22 9:47:26
 * @version: V1.0
 * @description: db1数据源配置类
 * @modify
 */
@Configuration
@MapperScan(basePackages = Db1DataSourceConfig.PACKAGE, sqlSessionFactoryRef = Db1DataSourceConfig.SQL_SESSION_FACTORY)
public class Db1DataSourceConfig {

    /**
     * sql session factory 的 bean 名称
     */
    static final String SQL_SESSION_FACTORY = "db1SqlSessionFactory";

    /**
     * 存放mapper接口的包路径
     */
    static final String PACKAGE = "icu.sunnyc.demo.mapper.db1";

    /**
     * xml文件路径
     */
    static final String MAPPER = "classpath:mappers/db1/*.xml";

    @Primary
    @Bean(name = "db1DataSource")
    @ConfigurationProperties("datasource.db1")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    /**
     * 事务管理器
     * @param dataSource dataSource
     * @return DataSourceTransactionManager
     */
    @Primary
    @Bean(name = "db1TransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(
            @Qualifier("db1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = SQL_SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(Db1DataSourceConfig.MAPPER));
        return sqlSessionFactoryBean.getObject();
    }

}
