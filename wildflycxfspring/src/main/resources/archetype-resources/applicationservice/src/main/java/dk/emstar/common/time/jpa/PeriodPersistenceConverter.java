#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.common.time.jpa;

import java.time.Period;
import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PeriodPersistenceConverter implements AttributeConverter<Period, String> {

    /**
    * @return an ISO-8601 representation of this duration.
    * @see Period${symbol_pound}toString()
    */
    @Override
    public String convertToDatabaseColumn(Period entityValue) {
        return Objects.toString(entityValue, null);
    }

    @Override
    public Period convertToEntityAttribute(String databaseValue) {
        return Period.parse(databaseValue);
    }
}
