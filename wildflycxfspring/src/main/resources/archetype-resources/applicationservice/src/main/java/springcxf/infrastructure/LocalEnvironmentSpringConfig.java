#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${parentArtifactId}.infrastructure;

import java.net.URI;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.connection.CachingConnectionFactory;

import dk.emstar.common.spring.Environments;

@PropertySource({"file:${symbol_dollar}{config_home}/${parentArtifactId}/local/environment.properties"})
@Configuration
@Profile(Environments.LOCAL)
public class LocalEnvironmentSpringConfig {
    @Bean(name = InfrastructureSpringConfig.LISTENER_CONNECTION_FACTORY)
    public ConnectionFactory listenerConnectionFactory(
            @Value("${symbol_dollar}{listner.connection.url}") String url) {
        ActiveMQConnectionFactory result = new ActiveMQConnectionFactory();
        result.setBrokerURL(url);
        return cachedConnectionFactoryFor(result);
    }

    @Bean(name = InfrastructureSpringConfig.LISTENER_QUEUE)
    public Queue listenerQueue(
            @Value("${symbol_dollar}{listner.connection.queuename}") String listnerQueueName) {
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
