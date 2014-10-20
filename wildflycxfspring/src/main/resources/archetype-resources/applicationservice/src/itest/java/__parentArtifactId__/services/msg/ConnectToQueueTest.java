#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${parentArtifactId}.services.msg;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JMSClientSpringConfig.class })
@TestPropertySource(properties = {
        "listner.connection.url=tcp://localhost:5000",
        "listner.connection.queuename=adapter.queue",
//        "listner.connection.queuename=interface.queue",
//        "mainBroker.url=broker:(tcp://localhost:5000)/server?persistent=false&useJmx=false&deleteAllMessagesOnStartup=true",
})
public class ConnectToQueueTest {

    @Inject
    private JmsTemplate jmsTemplate;

    @Test
    public void sendToAJMSListenerForAnotherQueue() throws Exception {
        jmsTemplate.send(o -> o.createTextMessage("testing from testclass"));
    }
}
