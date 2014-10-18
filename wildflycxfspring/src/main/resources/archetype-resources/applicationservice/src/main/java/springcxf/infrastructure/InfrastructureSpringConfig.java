#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${parentArtifactId}.infrastructure;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import ${package}.${parentArtifactId}.services.msg.TextMessageListener;

@ComponentScan(basePackageClasses = {InfrastructureSpringConfig.class})
@Configuration
public class InfrastructureSpringConfig {
    public static final String LISTENER_QUEUE = "listenerQueue";
    public static final String LISTENER_CONNECTION_FACTORY = "listenerConnectionFactory";

    @Bean
    public DefaultMessageListenerContainer container(TextMessageListener messageListener,
            @Qualifier(LISTENER_CONNECTION_FACTORY) ConnectionFactory listenerConnectionFactory, 
            @Qualifier(LISTENER_QUEUE) Queue listenerQueue) {
        DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();
        messageListenerContainer.setMessageListener(messageListener);
        messageListenerContainer.setConnectionFactory(listenerConnectionFactory);
        messageListenerContainer.setDestination(listenerQueue);
        messageListenerContainer.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        messageListenerContainer.setConcurrency("20-25");
        return messageListenerContainer;
    }
    
    @Bean
    public JmsTemplate onSameConnectionFactory( 
            @Qualifier(LISTENER_CONNECTION_FACTORY) ConnectionFactory listenerConnectionFactory,
            @Qualifier(LISTENER_QUEUE ) Queue listenerQueue) {
        JmsTemplate jmsTemplate = new JmsTemplate(listenerConnectionFactory);
        jmsTemplate.setDefaultDestination(listenerQueue);
        jmsTemplate.setReceiveTimeout(500);
        return jmsTemplate;
    }
    
}
