package gloncak.jozef.jsp.integratespringjsp2.view.person_data.row_selecting;

import gloncak.jozef.jsp.integratespringjsp2.model.Person;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.stereotype.Component;

@Component
@ViewScoped
public class PersonTableRowSelecting implements Serializable {

    private List<Person> selectedPersons;
    private Person selectedPerson;
    private String selectionMode = "multiple";

    public void onRowSelected(SelectEvent event) {
        select(event);
    }

    public void onRowUnselected(UnselectEvent event) {
        unselect(event);
    }
    
    public void onRadioSelected(SelectEvent event) {
        select(event);
    }
            
    public void onChecked(SelectEvent event) {
        select(event);
    }

    public void onUnchecked(UnselectEvent event) {
        unselect(event);
    }
    
    private void select(SelectEvent event) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Row for person with ID " + ((Person) event.getObject()).getId() + " was selected.", null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
    
    private void unselect(UnselectEvent event) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Row for person with ID " + ((Person) event.getObject()).getId() + " was unselected.", null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public List<Person> getSelectedPersons() {
        return selectedPersons;
    }

    public void setSelectedPersons(List<Person> selectedPersons) {
        this.selectedPersons = selectedPersons;
    }

    public String getSelectionMode() {
        return selectionMode;
    }

    public void setSelectionMode(String selectionMode) {
        this.selectionMode = selectionMode;
    }

    public Person getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
    }


}
