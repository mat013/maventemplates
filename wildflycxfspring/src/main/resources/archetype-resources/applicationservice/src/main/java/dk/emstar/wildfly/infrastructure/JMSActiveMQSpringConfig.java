#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.wildfly.infrastructure;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;

@Configuration
public class JMSActiveMQSpringConfig {
    @Bean(name = InfrastructureSpringConfig.LISTENER_CONNECTION_FACTORY)
    public ConnectionFactory listenerConnectionFactory(
            @Value("${symbol_dollar}{listner.connection.url}") String url) {
        ActiveMQConnectionFactory result = new ActiveMQConnectionFactory();
        result.setBrokerURL(url);
        return cachedConnectionFactoryFor(result);
    }
    
    @Bean(name = InfrastructureSpringConfig.LISTENER_INTERFACE_QUEUE)
    public Queue listenerInterfaceQueue(
            @Value("${symbol_dollar}{listner.interface.connection.queuename}") String listnerQueueName) {
        return new ActiveMQQueue(listnerQueueName);
    }
    
    @Bean(name = InfrastructureSpringConfig.LISTENER_ADAPTER_QUEUE)
    public Queue listenerAdapterQueue(
            @Value("${symbol_dollar}{listner.adapter.connection.queuename}") String listnerQueueName) {
        return new ActiveMQQueue(listnerQueueName);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public BrokerService mainBroker(
            @Value("${symbol_dollar}{mainBroker.url}") String url) throws Exception {
        return BrokerFactory.createBroker(url);
    }

    private ConnectionFactory cachedConnectionFactoryFor(ConnectionFactory connectionFactory) {
        CachingConnectionFactory result = new CachingConnectionFactory();
        result.setTargetConnectionFactory(connectionFactory);
        result.setSessionCacheSize(50);
        return result;
    }

}
