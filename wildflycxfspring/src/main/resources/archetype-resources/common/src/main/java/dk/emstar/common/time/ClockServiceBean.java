#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.${artifactId}.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Component;


@Component("clockService")
public class ClockServiceBean implements ClockService {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    @Override
    public LocalDate dateNow() {
        return LocalDate.now();
    }

    @Override
    public ZonedDateTime nowLocal() {
        return ZonedDateTime.now();
    }

    @Override
    public ZonedDateTime nowUTC() {
        return ZonedDateTime.now(ZoneId.of("UTC"));
    }

    @Override
    public LocalDate today() {
        return LocalDate.now();
    }

}
