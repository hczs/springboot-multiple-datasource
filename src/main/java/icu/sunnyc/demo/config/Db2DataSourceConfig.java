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

/**
 * @author: houcheng
 * @date: 2022/2/22 15:19:41
 * @version: V1.0
 * @description: db2数据源配置类
 * @modify
 */
@Configuration
@MapperScan(basePackages = Db2DataSourceConfig.PACKAGE, sqlSessionFactoryRef = Db2DataSourceConfig.SQL_SESSION_FACTORY)
public class Db2DataSourceConfig {


    /**
     * sql session factory 的 bean 名称
     */
    static final String SQL_SESSION_FACTORY = "db2SqlSessionFactory";

    /**
     * 存放mapper接口的包路径
     */
    static final String PACKAGE = "icu.sunnyc.demo.mapper.db2";

    /**
     * xml文件路径
     */
    static final String MAPPER = "classpath:mappers/db2/*.xml";

    @Bean(name = "db2DataSource")
    @ConfigurationProperties("datasource.db2")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    /**
     * 事务管理器
     * @param dataSource dataSource
     * @return DataSourceTransactionManager
     */
    @Bean(name = "db2TransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(
            @Qualifier("db2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = SQL_SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(Db2DataSourceConfig.MAPPER));
        return sqlSessionFactoryBean.getObject();
    }

}
