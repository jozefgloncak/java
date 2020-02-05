package gloncak.jozef.jsp.integratespringjsp2.converter;

import gloncak.jozef.jsp.integratespringjsp2.FormSettings;
import org.primefaces.convert.ClientConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.util.HashMap;
import java.util.Map;

//@Named
//@ApplicationScoped
@FacesConverter("birthNumberConverter")
public class BirthNumberConverter implements Converter<Long>, ClientConverter {

//    @Autowired
//    @Qualifier("ms1")
//    MessageSource ms;

    @Override
    public Long getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
//            FormSettings bean = context.getApplication().evaluateExpressionGet(context, "#{formSettings}", FormSettings.class);
//            ms.getMessage("err.convert.toNumber", null, bean.getLocaleInner());
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Must be number", ""), e);
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Long value) {
        return value.toString();
    }

    @Override
    public Map<String, Object> getMetadata() {
        Map<String, Object> result = new HashMap<>();
        result.put("data1", 45);
        return result;
    }

    @Override
    public String getConverterId() {
        return "birthNumberConverter";
    }
}
