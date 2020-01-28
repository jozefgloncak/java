# Lifecycle of JSF
* getter method of bean is called once form is displayed
* setter methods are called once formular is submitted 
    * after that, method specified in action attribute of button is called. There can be several action methods if
     there is
 several buttons on form.
  



# Redirecting
in action define
```
action="{name_of_new_page?faces-redirect=true}"
```
[more](https://stackoverflow.com/questions/15521451/how-to-navigate-in-jsf-how-to-make-url-reflect-current-page-and-not-previous-o)


# UI elements
## Selection elements
There is many UI elements which can be used for selecting:
* selecting several data items:
    * selectManyCheckbox
    * selectManyListbox
    * selectManyMenu - not vary practical (from list of data display only one item, which is scrollable)
* selecting one dat item
    * selectOneRadio
    * selectOneListbox
    * selectOneMenu
    
For each of previous (should be) option it is possible:
* enumerate options on frontend (value != label)
```xhtml
<h:select{selection_type} id="{id_of_ui_element}" value="#{bean.property}">
    <f:selectItem itemValue="{value1}" itemLabel="{label1}"/>
    <f:selectItem itemValue="{value1}" itemLabel="{label2}"/>
</h:select{selection_type}>
```
* enumerate options on backend (value != label) - in this option it is necessary to have bean with collection (e. g
. list) of available values. In next example it is on backend hidden under *propertyMenu*. In frontend it is
 referenced through variable *menu*. In example *propertyMenu* has 2 properties - name and code.
 
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;It is also possible to use as backend collection *Map* than key of map is
  display labels and value is display values).
```xhtml
<h:select{selection_type} id="{id_of_ui_element}" value="#{bean.property}">
    <f:selectItems value="#{bean2.propertyMenu}" var="menu"
                   itemLabel="#{menu.name}"
                   itemValue="#{menu.code}"/>
</h:select{selection_type}>
```
* enumerate options on backend (value == label)
```xhtml
<h:select{selection_type} id="{id_of_ui_element}" value="#{bean.property}">
    <f:selectItems value="#{bean2.propertyMenu}"/>
</h:select{selection_type}>
```



## Table
### Send current row
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
 
# Sources
[CoreServlets](http://www.coreservlets.com/JSF-Tutorial/jsf2/#Beans-1)