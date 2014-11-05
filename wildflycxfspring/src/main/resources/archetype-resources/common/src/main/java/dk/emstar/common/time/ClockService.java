#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.${artifactId}.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public interface ClockService {
    LocalDate today();
    LocalDateTime now();
    LocalDate dateNow();
    ZonedDateTime nowLocal();
    ZonedDateTime nowUTC();
}
