package address.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter {

    @Override
    public LocalDate unmarshal(Object v) throws Exception {
        return LocalDate.parse((String)v);
    }

    @Override
    public String marshal(Object v) throws Exception {
        return v.toString();
    }
}
