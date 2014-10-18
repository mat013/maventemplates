#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.jaxb.adapters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate>  {

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        if(localDate == null) {
            return null;
        }
        
        return localDate.format(DateTimeFormatter.ISO_DATE);
    }

    @Override
    public LocalDate unmarshal(String xmlData) throws Exception {
        if(xmlData == null) {
            return null;
        }
        
        return LocalDate.parse(xmlData, DateTimeFormatter.ISO_DATE);
    }
}
