package gloncak.jozef.jsp.integratespringjsp2;

import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * This converter make it possible to parse|format data from|to LocalDate.
 */
@FacesConverter("PickListConverter")
public class LocalDateConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {

            HtmlInputText p = (HtmlInputText) component;
            String dateValue = (String) p.getSubmittedValue();
            DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());

            Date parsedDate = null;
            try {
                parsedDate = dateInstance.parse(dateValue);
            } catch (ParseException e) {
                return null;
            }
            Instant instant = parsedDate.toInstant();
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
            DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
            Instant instant = ((LocalDate) value).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            return dateInstance.format(Date.from(instant));
        }

}
