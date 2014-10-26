#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services.msg;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TextMessageListenerBean implements MessageListener, TextMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage) message;
                String msg = tm.getText();
                logger.info("onMessage processed message '{}'", msg);
            }
        } catch (JMSException e) {
            logger.error(e.getMessage(), e);
        }
    }
}