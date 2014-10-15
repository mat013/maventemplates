#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.examples.jee7.ws.wildfly.example.springcfx.dependency;

import java.time.LocalDateTime;


public class CalendarServiceImpl implements CalendarService {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

}
