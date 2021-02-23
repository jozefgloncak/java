package gloncak.jozef.jsp.integratespringjsp2.view.dialog;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.view.ViewScoped;
import org.springframework.stereotype.Component;

@Component
@ViewScoped
public class Dialog implements Serializable {
    
    private Boolean modal = true;
    private Boolean resizable = true;
    private Boolean closeOnEscape = true;
    private Boolean closable = true;
    private Boolean draggable = true;
    private Boolean minimizable = true;
    private Boolean maximizable = true;
    
    private String showEffect;
    private String hideEffect;

    public Boolean getModal() {
        return modal;
    }

    public void setModal(Boolean modal) {
        this.modal = modal;
    }

    public Boolean getResizable() {
        return resizable;
    }

    public void setResizable(Boolean resizable) {
        this.resizable = resizable;
    }

    public Boolean getCloseOnEscape() {
        return closeOnEscape;
    }

    public void setCloseOnEscape(Boolean closeOnEscape) {
        this.closeOnEscape = closeOnEscape;
    }

    public Boolean getClosable() {
        return closable;
    }

    public void setClosable(Boolean closable) {
        this.closable = closable;
    }

    public Boolean getDraggable() {
        return draggable;
    }

    public void setDraggable(Boolean draggable) {
        this.draggable = draggable;
    }

    public Boolean getMinimizable() {
        return minimizable;
    }

    public void setMinimizable(Boolean minimizable) {
        this.minimizable = minimizable;
    }

    public Boolean getMaximizable() {
        return maximizable;
    }

    public void setMaximizable(Boolean maximizable) {
        this.maximizable = maximizable;
    }

    public String getShowEffect() {
        return showEffect;
    }

    public void setShowEffect(String showEffect) {
        this.showEffect = showEffect;
    }

    public String getHideEffect() {
        return hideEffect;
    }

    public void setHideEffect(String hideEffect) {
        this.hideEffect = hideEffect;
    }
   
}