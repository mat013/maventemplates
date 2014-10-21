#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${parentArtifactId}.infrastructure;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Session;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ${package}.${parentArtifactId}.domain.User;
import ${package}.${parentArtifactId}.infrastructure.domain.UserRepositoryBean;
import ${package}.${parentArtifactId}.services.msg.TextMessageListener;
import ${package}.${parentArtifactId}.services.msg.TextMessageUsingAdapterListener;

@ComponentScan(basePackageClasses = {InfrastructureSpringConfig.class, UserRepositoryBean.class})
@Configuration
@EnableTransactionManagement
public class InfrastructureSpringConfig {
    public static final String LISTENER_INTERFACE_QUEUE = "listenerInterfaceQueue";
    public static final String LISTENER_ADAPTER_QUEUE = "listenerAdapterQueue";
    public static final String LISTENER_CONNECTION_FACTORY = "listenerConnectionFactory";

    @Bean
    public MessageListenerAdapter textMessageListenerUsingAdapter(TextMessageUsingAdapterListener textMessageUsingAdapterListener) {
        MessageListenerAdapter messageListener
                = new MessageListenerAdapter(textMessageUsingAdapterListener);
        messageListener.setDefaultListenerMethod("onReceive");
        return messageListener;
    }

    @Bean
    public DefaultMessageListenerContainer textMessageListenerContainerUsingAdapter(
             @Qualifier("textMessageListenerUsingAdapter") MessageListenerAdapter messageListener,
             @Qualifier(LISTENER_CONNECTION_FACTORY) ConnectionFactory listenerConnectionFactory,
             @Qualifier(LISTENER_ADAPTER_QUEUE) Queue listenerQueue) {
        DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();
        messageListenerContainer.setMessageListener(messageListener);
        messageListenerContainer.setConnectionFactory(listenerConnectionFactory);
        messageListenerContainer.setDestination(listenerQueue);
        messageListenerContainer.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        messageListenerContainer.setConcurrency("20-25");
        return messageListenerContainer;
    }
    
    @Bean
    public JmsTemplate adapterOnSameConnectionFactory( 
            @Qualifier(LISTENER_CONNECTION_FACTORY) ConnectionFactory listenerConnectionFactory,
            @Qualifier(LISTENER_ADAPTER_QUEUE ) Queue listenerQueue) {
        JmsTemplate jmsTemplate = new JmsTemplate(listenerConnectionFactory);
        jmsTemplate.setDefaultDestination(listenerQueue);
        jmsTemplate.setReceiveTimeout(500);
        return jmsTemplate;
    }

   @Bean
    public DefaultMessageListenerContainer textMessageListenerContainerUsingInterface(TextMessageListener messageListener,
            @Qualifier(LISTENER_CONNECTION_FACTORY) ConnectionFactory listenerConnectionFactory, 
            @Qualifier(LISTENER_INTERFACE_QUEUE) Queue listenerQueue) {
        DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();
        messageListenerContainer.setMessageListener(messageListener);
        messageListenerContainer.setConnectionFactory(listenerConnectionFactory);
        messageListenerContainer.setDestination(listenerQueue);
        messageListenerContainer.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        messageListenerContainer.setConcurrency("20-25");
        return messageListenerContainer;
    }

    @Bean
    public JmsTemplate interfaceOnSameConnectionFactory( 
            @Qualifier(LISTENER_CONNECTION_FACTORY) ConnectionFactory listenerConnectionFactory,
            @Qualifier(LISTENER_INTERFACE_QUEUE ) Queue listenerQueue) {
        JmsTemplate jmsTemplate = new JmsTemplate(listenerConnectionFactory);
        jmsTemplate.setDefaultDestination(listenerQueue);
        jmsTemplate.setReceiveTimeout(500);
        return jmsTemplate;
    }

    // ----- Database
    
    
    @Value("${symbol_dollar}{hibernate.dialect}")
    private String hibernateDialect;
    
    @Value("${symbol_dollar}{hibernate.show_sql}")
    private String hibernateShowSql;
    
    @Value("${symbol_dollar}{hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddlAuto;

    @Bean
    public DataSource dataSource(
            @Value("${symbol_dollar}{${parentArtifactId}.driverName}") String jdbcDatabaseDriverName,
            @Value("${symbol_dollar}{${parentArtifactId}.databaseUrl}") String jdbcDatabaseUrl,
            @Value("${symbol_dollar}{${parentArtifactId}.databaseUsename}") String jdbcDatabaseUsername,
            @Value("${symbol_dollar}{${parentArtifactId}.databasePassword}") String jdbcDatabasePassword) {
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
        localContainerEntityManagerFactoryBean.setPackagesToScan(new String[] { User.class.getPackage().getName() });

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
