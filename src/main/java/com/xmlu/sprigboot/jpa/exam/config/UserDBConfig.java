package com.xmlu.sprigboot.jpa.exam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 业务数据源配置
 * 将数据源注入到实体管理器工厂，
 * 配置repository、domian的位置
 *
 * @author xiumin.lu
 * @create 2018-9-20
 **/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        //配置连接工厂entityManagerFactory
        entityManagerFactoryRef = "entityManagerFactory",
        //配置事物管理器transactionManager
        transactionManagerRef = "transactionManager",
        //设置子接口的dao（repo）所在包位置,基础接口不用配置
        basePackages = {"com.xmlu.sprigboot.jpa.exam.dao"}
)
public class UserDBConfig {
    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    /**
     * @param builder
     * @return
     */
    @Bean(name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                //设置数据源
                .dataSource(dataSource)
                //设置数据源属性
                .properties(getVendorProperties(dataSource))
                //设置实体类所在位置.扫描所有带有@Entity注解的类
                .packages("com.xmlu.sprigboot.jpa.exam.dao.model")
                //Spring会将EntityManagerFactory注入到Repository之中,有了EntityManagerFactory之后,
                //Repository就能用它来创建EntityManager了,然后Entity就可以针对数据库执行操作
                .persistenceUnit("persistenceUnit")
                .build();

    }

    private Map<String, String> getVendorProperties(@Qualifier("dataSource") DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    /**
     * 事物管理器
     *
     * @param builder EntityManagerFactoryBuilder
     * @return PlatformTransactionManager
     */
    @Bean("transactionManager")
    //@Primary
    PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }
}
