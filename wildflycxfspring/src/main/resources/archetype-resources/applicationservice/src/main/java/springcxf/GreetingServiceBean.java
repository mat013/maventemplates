#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${parentArtifactId};
 
import javax.inject.Inject;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dk.emstar.time.CalendarService;
import dk.emstar.webservice.example.Greet;
import dk.emstar.webservice.example.GreetResponse;
import dk.emstar.webservice.example.GreetService;

@Service
@WebService
public class GreetingServiceBean implements GreetService {
 
    @Inject
    @Qualifier("calendarService")
    private CalendarService calendarService;


    @Override
    public GreetResponse greet(Greet request) {
        GreetResponse response = new GreetResponse();
        response.setReturn("Hello " + request.getNavn() + " time is " + calendarService.now());
        
        return response;
    }
}