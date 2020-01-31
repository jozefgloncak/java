package gloncak.jozef.jsp.integratespringjsp2;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.event.ValueChangeEvent;
import java.util.Locale;

@Component
@SessionScope
public class FormSettings {
    private String locale;
    private Locale localeInner = new Locale("en");

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
        this.localeInner = new Locale(locale);
    }

    public Locale getLocaleInner() {
        return localeInner;
    }

    public void setLocaleInner(Locale localeInner) {
        this.localeInner = localeInner;
    }
}
