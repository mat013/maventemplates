#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${parentArtifactId}.services.ws;
 
import javax.inject.Inject;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import dk.emstar.common.time.ClockService;
import dk.emstar.example.greeting.Greet;
import dk.emstar.example.greeting.GreetResponse;
import dk.emstar.example.greeting.GreetService;
import dk.emstar.example.greeting.SendMessage;
import dk.emstar.example.greeting.SendMessageResponse;

@Service
@WebService
public class GreetingServiceBean implements GreetService {
    private static final Logger logger = LoggerFactory.getLogger(GreetingServiceBean.class);
    
    @Inject
    private ClockService clockService;

    @Value("${symbol_dollar}{environment}")
    private String environment;

    @Inject
    @Qualifier("onSameConnectionFactory")
    private JmsTemplate onSameConnectionFactory;

    @Override
    public GreetResponse greet(Greet request) {
        GreetResponse response = new GreetResponse();
        response.setText("Hello " + request.getNavn() + " time is " + clockService.now() + " and the environment is " + environment);
        response.setDateTime(clockService.now());
        response.setDate(clockService.dateNow());

        return response;
    }

    @Override
    public SendMessageResponse sendMessage(SendMessage request) {
        String textMessage = request.getIn();
        onSameConnectionFactory.send(o -> o.createTextMessage(textMessage));
        SendMessageResponse result = new SendMessageResponse();
        result.setOut("done!");
        return result;
    }
}