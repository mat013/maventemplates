#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${parentArtifactId}.services.msg;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JMSClientSpringConfig {
    @Bean
    public JmsTemplate onSameConnectionFactory( 
            ConnectionFactory listenerConnectionFactory,
            Queue listenerQueue) {
        JmsTemplate jmsTemplate = new JmsTemplate(listenerConnectionFactory);
        jmsTemplate.setDefaultDestination(listenerQueue);
        jmsTemplate.setReceiveTimeout(500);
        return jmsTemplate;
    }
    
    @Bean
    public ConnectionFactory listenerConnectionFactory(
            @Value("${symbol_dollar}{listner.connection.url}") String url) {
        ActiveMQConnectionFactory result = new ActiveMQConnectionFactory();
        result.setBrokerURL(url);
        return result;
    }

    @Bean
    public Queue listenerQueue(
            @Value("${symbol_dollar}{listner.connection.queuename}") String listnerQueueName) {
        return new ActiveMQQueue(listnerQueueName);
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
       return propertySourcesPlaceholderConfigurer;
    }
}
