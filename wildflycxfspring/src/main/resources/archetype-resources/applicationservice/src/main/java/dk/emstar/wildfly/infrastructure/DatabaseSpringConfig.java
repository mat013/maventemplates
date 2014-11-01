#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.wildfly.infrastructure;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import dk.emstar.common.time.jpa.LocalDatePersistenceConverter;
import dk.emstar.wildfly.domain.User;

@Configuration
@EnableTransactionManagement
public class DatabaseSpringConfig {
    @Value("${symbol_dollar}{hibernate.dialect}")
    private String hibernateDialect;
    
    @Value("${symbol_dollar}{hibernate.show_sql}")
    private String hibernateShowSql;
    
    @Value("${symbol_dollar}{hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddlAuto;

    @Bean
    public DataSource dataSource(
            @Value("${symbol_dollar}{springcxf.driverName}") String jdbcDatabaseDriverName,
            @Value("${symbol_dollar}{springcxf.databaseUrl}") String jdbcDatabaseUrl,
            @Value("${symbol_dollar}{springcxf.databaseUsename}") String jdbcDatabaseUsername,
            @Value("${symbol_dollar}{springcxf.databasePassword}") String jdbcDatabasePassword) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(jdbcDatabaseDriverName);
        dataSource.setUrl(jdbcDatabaseUrl);
        dataSource.setUsername(jdbcDatabaseUsername);
        dataSource.setPassword(jdbcDatabasePassword);

        return dataSource;
    }
    
    @Bean(destroyMethod="destroy")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean 
            = new LocalContainerEntityManagerFactoryBean();
        
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setPersistenceUnitName("test");
        localContainerEntityManagerFactoryBean.setPackagesToScan(new String[] { 
                User.class.getPackage().getName(),
                LocalDatePersistenceConverter.class.getPackage().getName() });

        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        localContainerEntityManagerFactoryBean.setJpaProperties(hibernateProperties());

        return localContainerEntityManagerFactoryBean;
    }

    protected Properties hibernateProperties(
            ) {
        Properties hibernateProperties = new Properties();

//        hibernateProperties.setProperty("hibernate.dialect", hibernateDialect);
//        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
//        hibernateProperties.setProperty("hibernate.show_sql", hibernateShowSql);
//        hibernateProperties.setProperty("hibernate.use_sql_comments",env.getProperty("hibernate.use_sql_comments"));
//        hibernateProperties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        
//        <prop key="hibernate.hbm2ddl.auto">create</prop> 
//        <prop key="hibernate.hbm2ddl.import_files">import.sql</prop>  

        // hibernateProperties.setProperty("hibernate.generate_statistics",
        // env.getProperty("hibernate.generate_statistics"));
        // hibernateProperties.setProperty("javax.persistence.validation.mode",
        // env.getProperty("javax.persistence.validation.mode"));

        // Audit History flags
        // hibernateProperties.setProperty("org.hibernate.envers.store_data_at_delete",
        // env.getProperty("org.hibernate.envers.store_data_at_delete"));
        // hibernateProperties.setProperty("org.hibernate.envers.global_with_modified_flag",
        // env.getProperty("org.hibernate.envers.global_with_modified_flag"));

        return hibernateProperties;
    }
    
    @Bean
    public JpaTransactionManager transactionManagerJPA(EntityManagerFactory entityManagerFactory){
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory(entityManagerFactory);

       return transactionManager;
    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
    

}
