#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.wildfly.services.msg;

import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TextMessageUsingAdapterListenerBean implements TextMessageUsingAdapterListener {

    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @Override
    public void onReceive(String message) {
        logger.info("onReceive processed message '{}'", message);
    }

}