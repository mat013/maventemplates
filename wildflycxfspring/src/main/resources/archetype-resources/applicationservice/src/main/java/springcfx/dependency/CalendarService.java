#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${parentArtifactId}.dependency;

import java.time.LocalDateTime;

public interface CalendarService {
    LocalDateTime now();
}
