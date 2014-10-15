package dk.emstar.jaxb.adapters;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime>  {

    public ZoneId utcTimeZone = ZoneId.of("UTC");
    
    @Override
    public String marshal(LocalDateTime localDateTime) throws Exception {
        if(localDateTime == null) {
            return null;
        }
        
        return localDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(utcTimeZone)
                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    @Override
    public LocalDateTime unmarshal(String xmlData) throws Exception {
        if(xmlData == null) {
            return null;
        }
        
        return ZonedDateTime.parse(xmlData, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                .toLocalDateTime();
    }
}
