#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${parentArtifactId}.services;
 
import javax.inject.Inject;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dk.emstar.common.time.ClockService;
import dk.emstar.example.greeting.Greet;
import dk.emstar.example.greeting.GreetResponse;
import dk.emstar.example.greeting.GreetService;

@Service
@WebService
public class GreetingServiceBean implements GreetService {
 
    @Inject
    private ClockService clockService;

    @Value("${symbol_dollar}{environment}")
    private String environment;

    @Override
    public GreetResponse greet(Greet request) {
        GreetResponse response = new GreetResponse();
        response.setText("Hello " + request.getNavn() + " time is " + clockService.now() + " and the environment is " + environment);
        response.setDateTime(clockService.now());
        response.setDate(clockService.dateNow());
        
        return response;
    }
}