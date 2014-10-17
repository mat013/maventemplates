#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.time;

import java.time.LocalDateTime;

public interface CalendarService {
    LocalDateTime now();
}
