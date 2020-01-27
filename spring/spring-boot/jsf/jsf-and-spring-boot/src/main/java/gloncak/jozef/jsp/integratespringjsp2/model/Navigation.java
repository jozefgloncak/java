package gloncak.jozef.jsp.integratespringjsp2.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Navigation {

    private String pageId;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String resolvePage() {
        switch (pageId) {
            case "1": return "page2";
            case "2": return "resolve-day-of-week";
            default: return "menu";
        }

    }
}
