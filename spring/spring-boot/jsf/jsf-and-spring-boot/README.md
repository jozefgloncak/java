# Redirecting
in action define
```
action="{name_of_new_page?faces-redirect=true}"
```
[more](https://stackoverflow.com/questions/15521451/how-to-navigate-in-jsf-how-to-make-url-reflect-current-page-and-not-previous-o)

# Table
## Send current row
```xhtml
    <h:form>
        <h:dataTable value="#{personData.persons}" var="person">
......
            <h:column>
                <f:facet name="header">Delete</f:facet>
                <h:commandButton value="Delete"
                                 action="#{personData.deletePerson(person)}">
                </h:commandButton>
            </h:column>
.....
        </h:dataTable>
    </h:form>

```
in backend there has to be model classs **PersonData** with method **deletePerson(Person person)** which accepts one
 input parameter of type Person.