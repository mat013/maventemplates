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
import dk.emstar.example.greeting.CreateUser;
import dk.emstar.example.greeting.CreateUserBMT;
import dk.emstar.example.greeting.CreateUserBMTResponse;
import dk.emstar.example.greeting.CreateUserCrud;
import dk.emstar.example.greeting.CreateUserCrudResponse;
import dk.emstar.example.greeting.CreateUserResponse;
import dk.emstar.example.greeting.Greet;
import dk.emstar.example.greeting.GreetResponse;
import dk.emstar.example.greeting.GreetService;
import dk.emstar.example.greeting.SendMessageUsingAdapter;
import dk.emstar.example.greeting.SendMessageUsingAdapterResponse;
import dk.emstar.example.greeting.SendMessageUsingInterface;
import dk.emstar.example.greeting.SendMessageUsingInterfaceResponse;
import dk.emstar.example.header.RequestHeader;
import ${package}.${parentArtifactId}.domain.User;
import ${package}.${parentArtifactId}.services.domain.UserGeneratedRepository;
import ${package}.${parentArtifactId}.services.domain.UserRepository;

@Service
@WebService
public class GreetingServiceBean implements GreetService {
    private static final Logger logger = LoggerFactory.getLogger(GreetingServiceBean.class);
    
    @Inject
    private ClockService clockService;

    @Value("${symbol_dollar}{environment}")
    private String environment;

    @Inject
    @Qualifier("interfaceOnSameConnectionFactory")
    private JmsTemplate interfaceOnSameConnectionFactory;

    @Inject
    @Qualifier("adapterOnSameConnectionFactory")
    private JmsTemplate adapterOnSameConnectionFactory;

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserGeneratedRepository userGeneratedRepository;

    @Override
    public GreetResponse greet(RequestHeader requestHeader, Greet request) {
        GreetResponse response = new GreetResponse();
        response.setText("Hello " + request.getNavn() + " time is " + clockService.now() + " and the environment is " + environment);
        response.setDateTime(clockService.now());
        response.setDate(clockService.dateNow());

        return response;
    }

    @Override
    public SendMessageUsingAdapterResponse sendMessageUsingAdapter(SendMessageUsingAdapter request) {
        String textMessage = request.getMessage();
        adapterOnSameConnectionFactory.send(o -> o.createTextMessage(textMessage));
        SendMessageUsingAdapterResponse result = new SendMessageUsingAdapterResponse();
        return result;
    }

    @Override
    public SendMessageUsingInterfaceResponse sendMessageUsingInterface(SendMessageUsingInterface request) {
        String textMessage = request.getMessage();
        interfaceOnSameConnectionFactory.send(o -> o.createTextMessage(textMessage));
        SendMessageUsingInterfaceResponse result = new SendMessageUsingInterfaceResponse();
        return result;
    }

    @Override
    public CreateUserCrudResponse createUserCrud(CreateUserCrud parameters) {
        CreateUserCrudResponse result = new CreateUserCrudResponse();
        User user = new User();
        user.setUsername(parameters.getUsername());
        userRepository.createUser(user);
        return result;
    }

    @Override
    public CreateUserBMTResponse createUserBMT(CreateUserBMT parameters) {
        CreateUserBMTResponse result = new CreateUserBMTResponse();
        User user = new User();
        user.setUsername(parameters.getUsername());
        userRepository.createUserBMT(user);
        return result;
    }

    @Override
    public CreateUserResponse createUser(CreateUser parameters) {
        CreateUserResponse result = new CreateUserResponse();
        User user = new User();
        user.setUsername(parameters.getUsername());
        userGeneratedRepository.save(user);
        return result;
    }
}