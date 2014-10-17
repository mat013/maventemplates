#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.time;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;


@Component
public class CalendarServiceImpl implements CalendarService {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

}
