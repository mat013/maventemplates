#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.common.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public interface ClockService {
    LocalDateTime now();
    LocalDate dateNow();
    ZonedDateTime nowLocal();
    ZonedDateTime nowUTC();
}
