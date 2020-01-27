package gloncak.jozef.jsp.integratespringjsp2;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;

@Component
@RequestScope
public class NavigationController implements Serializable {
    private String pageId;

    public String resolvePage() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params =
                fc.getExternalContext().getRequestParameterMap();
        pageId = params.get("pageId");

        String result;
        switch (pageId) {
            case "1":
                result = "page2";
                break;
            case "2":
                result = "resolve-day-of-week";
                break;
            default:
                result = "menu";
        }

        result = result + "?faces-redirect=true";
        return result;

    }
}