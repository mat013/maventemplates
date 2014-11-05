#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.${artifactId}.time.jpa;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OffsetDateTimePersistenceConverter implements
    AttributeConverter<OffsetDateTime, String> {

    /**
    * @return a value as a String such as 2014-12-03T10:15:30+01:00
    * @see OffsetDateTime${symbol_pound}toString()
    */
    @Override
    public String convertToDatabaseColumn(OffsetDateTime entityValue) {
        return Objects.toString(entityValue, null);
    }

    @Override
    public OffsetDateTime convertToEntityAttribute(String databaseValue) {
        return OffsetDateTime.parse(databaseValue);
    }
}
