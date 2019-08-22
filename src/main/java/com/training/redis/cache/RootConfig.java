package com.training.redis.cache;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @Description TODO
 * @date 2019/8/21
 */
@Configuration
// 定义Spring 扫描的包
//@ComponentScan("com.training.redis.cache.service")
//@ComponentScan("com.training.redis.cache.mapper")
@ComponentScan("com.training.redis.cache*")
// 使用事务驱动管理器
@EnableTransactionManagement
// 实现接口TransactionManagementConfigurer，这样可以配置注解驱动事务
public class RootConfig {

    private DataSource dataSource = null;
    /**
     * @Title: initDataSource
     * @Description: 配置数据库
     * @return: DataSource
     * @throws IOException
     */
    @Bean(name = "dataSource")
    public DataSource initDataSource() throws IOException {
        if (dataSource != null) {
            return dataSource;
        }
        Properties props = new Properties();
        props.load(RootConfig.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        props.setProperty("driverClassName", props.getProperty("jdbc.driverClasss"));
        props.setProperty("url", props.getProperty("jdbc.jdbcUrl"));
        props.setProperty("username", props.getProperty("jdbc.username"));
        props.setProperty("password", props.getProperty("jdbc.password"));
        try {
            dataSource = BasicDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    /**
     * @Title: initSqlSessionFactory
     * @Description: 配置SqlSessionFactoryBean
     * @return: SqlSessionFactoryBean
     * @throws IOException
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean initSqlSessionFactory() throws IOException {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(initDataSource());
        // 配置MyBatis配置文件
        Resource resource = new ClassPathResource("mybatis/mybatis-config.xml");

        sqlSessionFactory.setConfigLocation(resource);
        sqlSessionFactory.setTypeAliasesPackage("com.training.redis.cache.mapper");

       // PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //sqlSessionFactory.setMapperLocations(
             //   resolver.getResources("classpath:com/training/redis/cache/mapper/*.xml"));
        return sqlSessionFactory;
    }

    /**
     *
     *
     * @Title: initMapperScannerConfigurer
     *
     * @Description: 通过自动扫描，发现MyBatis Mapper接口
     * @return: MapperScannerConfigurer Mapper扫描器
     */
    @Bean
    public MapperScannerConfigurer initMapperScannerConfigurer() {
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        // 扫描包
        msc.setBasePackage("com.training.redis.cache.mapper");
        msc.setSqlSessionFactoryBeanName("sqlSessionFactory");
        // 如果写的比较泛型的话 ， 配置扫描的指定注解
        //  msc.setBasePackage("com.training.redis.cache.*");
        // 区分注解扫描
       //   msc.setAnnotationClass(Repository.class);
        return msc;
    }

    /**
     * 实现接口方法，注册注解事务，当@Transactional 使用的时候产生数据库事务
     */
    @Bean(name = "annotationDrivenTransactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager transactionManager = null;
        try {
            transactionManager = new DataSourceTransactionManager();
            transactionManager.setDataSource(initDataSource());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactionManager;
    }

}
