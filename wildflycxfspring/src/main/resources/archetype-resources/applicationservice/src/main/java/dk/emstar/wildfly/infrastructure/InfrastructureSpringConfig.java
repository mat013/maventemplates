#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.wildfly.infrastructure;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import dk.emstar.wildfly.infrastructure.domain.UserRepositoryBean;
import dk.emstar.wildfly.services.msg.TextMessageListener;
import dk.emstar.wildfly.services.msg.TextMessageUsingAdapterListener;

@Import({DatabaseSpringConfig.class, JMSActiveMQSpringConfig.class})
@Configuration
@ComponentScan(basePackageClasses = {InfrastructureSpringConfig.class, UserRepositoryBean.class})
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

}
