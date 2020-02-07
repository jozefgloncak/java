package gloncak.jozef.jsp.integratespringjsp2.view.person_data.editing;

import gloncak.jozef.jsp.integratespringjsp2.model.Person;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component("rowEditView")
@ViewScoped
public class PersonTableRowEditingTabView implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(PersonTableRowEditingTabView.class);

    private Map<Integer, Person> justEditedPersons = new HashMap<>();

    public PersonTableRowEditingTabView() {
    }

    public void onRowEdit(RowEditEvent event) {
        Person personAfterEdit = (Person) event.getObject();
        Person personBeforeEdit = justEditedPersons.remove(personAfterEdit.getId());
        if (personAfterEdit.equals(personBeforeEdit)) {
            LOG.debug("No change for person with ID {}. Nothing to store to data source. Current editing count - {} persons.", personAfterEdit.getId(), justEditedPersons.size());
        } else {
            LOG.debug("Row editing save. Editated person with ID {} saved. Current editing count - {} persons", ((Person) event.getObject()).getId(), justEditedPersons.size());
        }
    }

    public void onRowCancel(RowEditEvent event) {
        justEditedPersons.remove(((Person) event.getObject()).getId());
        LOG.debug("Row editing cancel. Editing of person with ID {} canceled. Current editing count - {} persons", ((Person) event.getObject()).getId(), justEditedPersons.size());
    }

    public void rowEditInit(RowEditEvent event) throws CloneNotSupportedException {
        Person clonedPerson = ((Person) event.getObject()).clone();
        justEditedPersons.put(clonedPerson.getId(), clonedPerson);
        LOG.debug("Row editing init. Cloning original person with ID {}. Current editing count - {} persons", clonedPerson.getId(), justEditedPersons.size());
    }

}
