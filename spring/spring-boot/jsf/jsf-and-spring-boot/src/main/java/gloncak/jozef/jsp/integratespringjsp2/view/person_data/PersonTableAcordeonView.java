package gloncak.jozef.jsp.integratespringjsp2.view.person_data;

import org.springframework.stereotype.Component;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.view.ViewScoped;

@Component
@ViewScoped
public class PersonTableAcordeonView implements Serializable {
    private Boolean expandAllTabs;
    private String expandedTabs = "3";

    public void expandCollapseAllTabs() {
        expandedTabs = expandAllTabs ? "0,1,2,3" : "";
    }

    public Boolean getExpandAllTabs() {
        return expandAllTabs;
    }

    public void setExpandAllTabs(Boolean expandAllTabs) {
        this.expandAllTabs = expandAllTabs;
    }

    public String getExpandedTabs() {
        return expandedTabs;
    }

    public void setExpandedTabs(String expandedTabs) {
        this.expandedTabs = expandedTabs;
    }
    
}
