package XML;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class LocalDateConverter extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(String data){
        return LocalDate.parse(data);
    }

    @Override
    public String marshal(LocalDate localDate){
        return localDate.toString();
    }
}
